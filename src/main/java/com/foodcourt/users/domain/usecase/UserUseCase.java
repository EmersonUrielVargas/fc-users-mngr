package com.foodcourt.users.domain.usecase;

import com.foodcourt.users.domain.api.IUserServicePort;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.exception.RoleNotFoundException;
import com.foodcourt.users.domain.exception.UserNotFoundException;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IAssignmentUserPersistencePort;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
import com.foodcourt.users.domain.validators.UserValidator;

public class UserUseCase implements IUserServicePort{

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IRolePersistencePort rolePersistencePort;
    private final IAssignmentUserPersistencePort assignmentUserPersistencePort;


    public UserUseCase(IUserPersistencePort userPersistencePort,
                       IPasswordEncoderPort passwordEncoderPort,
                       IRolePersistencePort rolePersistencePort,
                       IAssignmentUserPersistencePort assignmentUserPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.rolePersistencePort = rolePersistencePort;
        this.assignmentUserPersistencePort = assignmentUserPersistencePort;
    }


    @Override
    public void createOwner(User userToCreate) {
        UserValidator.validateValidBasicUser(userToCreate);
        UserValidator.validateEmail(userToCreate);
        UserValidator.validatePhone(userToCreate);
        UserValidator.validateIDNumber(userToCreate);
        User validatedUser = UserValidator.validateAge(userToCreate);
        Role ownerRol = rolePersistencePort.getByName(UserRole.OWNER)
                .orElseThrow(RoleNotFoundException::new);
        validatedUser.setRole(ownerRol);
        validatedUser.setPassword(passwordEncoderPort.encoder(userToCreate.getPassword()));

        userPersistencePort.saveUser(validatedUser);
    }

    @Override
    public String getUserRoleById(Long idUser) {
        User userFound = userPersistencePort.getUserById(idUser)
                .orElseThrow(UserNotFoundException::new);
        UserRole roleName = userFound.getRole().getName();
        return roleName.toString();
    }

    @Override
    public void createEmployee(User employeeToCreate) {
        User validatedUser = validateCreationUser(employeeToCreate, UserRole.EMPLOYEE);
        User userCreated = userPersistencePort.saveUser(validatedUser);
        assignmentUserPersistencePort.saveAssignment(userCreated.getId());
    }

    @Override
    public void createClient(User clientToCreate) {
        User validatedUser = validateCreationUser(clientToCreate, UserRole.CLIENT);
        userPersistencePort.saveUser(validatedUser);
    }

    @Override
    public User findById(Long userId) {
        return userPersistencePort.getUserById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    private User validateCreationUser(User user,UserRole userRole){
        UserValidator.validateValidBasicUser(user);
        UserValidator.validateEmail(user);
        UserValidator.validatePhone(user);
        User validatedUser = UserValidator.validateIDNumber(user);
        Role roleFound = rolePersistencePort.getByName(userRole)
                .orElseThrow(RoleNotFoundException::new);
        if (!roleFound.getId().equals(user.getRole().getId())){
            throw new DomainException(Constants.ROLE_USER_NO_MATCHED);
        }
        validatedUser.setRole(roleFound);
        validatedUser.setPassword(passwordEncoderPort.encoder(user.getPassword()));
        return validatedUser;
    }
}
