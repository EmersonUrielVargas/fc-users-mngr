package com.foodcourt.users.domain.usecase;

import com.foodcourt.users.domain.api.IUserServicePort;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IAuthenticationPort;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
import com.foodcourt.users.domain.validators.UserValidator;

import java.util.Optional;

public class UserUseCase implements IUserServicePort{

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IRolePersistencePort rolePersistencePort;


    public UserUseCase(IUserPersistencePort userPersistencePort,
                       IPasswordEncoderPort passwordEncoderPort,
                       IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.rolePersistencePort = rolePersistencePort;
    }


    @Override
    public void createOwner(User userToCreate) {

        UserValidator.validateEmail(userToCreate);
        UserValidator.validatePhone(userToCreate);
        UserValidator.validateIDNumber(userToCreate);
        User validatedUser = UserValidator.validateAge(userToCreate);
        Role ownerRol = rolePersistencePort.getByName(UserRole.OWNER)
                .orElseThrow(() -> new DomainException(Constants.ROLE_NO_FOUND));
        validatedUser.setRole(ownerRol);
        validatedUser.setPassword(passwordEncoderPort.encoder(userToCreate.getPassword()));

        userPersistencePort.saveUser(validatedUser);
    }

    @Override
    public Optional<UserRole> getUserRoleById(Long idUser) {
        User userFound = userPersistencePort.getUserById(idUser)
                .orElseThrow(()-> new DomainException(Constants.USER_NO_FOUND));
        return Optional.ofNullable(userFound.getRole())
                .map(Role::getName);
    }


}
