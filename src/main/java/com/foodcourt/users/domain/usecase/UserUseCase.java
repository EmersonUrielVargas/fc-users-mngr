package com.foodcourt.users.domain.usecase;

import com.foodcourt.users.domain.api.IUserServicePort;
import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
import com.foodcourt.users.domain.validators.UserValidator;
import reactor.core.publisher.Mono;

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
    public Mono<Void> createOwner(User userToCreate) {
        return Mono.just(userToCreate)
                .flatMap(UserValidator::validateAge)
                .flatMap(UserValidator::validateEmail)
                .flatMap(UserValidator::validatePhone)
                .flatMap(UserValidator::validateIDNumber)
                .flatMap(user ->
                    rolePersistencePort.getByName(UserRole.OWNER)
                        .map(ownerRole -> {
                            user.setRole(ownerRole);
                            user.setPassword(passwordEncoderPort.encoder(user.getPassword()));
                            return user;
                        })
                )
                .flatMap(userPersistencePort::saveUser);
    }
}
