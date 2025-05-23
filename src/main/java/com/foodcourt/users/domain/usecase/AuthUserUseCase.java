package com.foodcourt.users.domain.usecase;

import com.foodcourt.users.domain.api.IAuthUserServicePort;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IAuthenticationPort;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;

import java.util.Optional;

public class AuthUserUseCase implements IAuthUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IAuthenticationPort authenticationPort;


    public AuthUserUseCase(IUserPersistencePort userPersistencePort,
                           IPasswordEncoderPort passwordEncoderPort,
                           IRolePersistencePort rolePersistencePort, IAuthenticationPort authenticationPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.authenticationPort = authenticationPort;
    }

    @Override
    public Optional<String> loginUser(String email, String password) {
        User userFound = userPersistencePort.getUserByEmail(email)
                .orElseThrow(()->new DomainException(Constants.USER_NO_FOUND));
        if (passwordEncoderPort.matches(password, userFound.getPassword())){
            return authenticationPort.generateToken(userFound);
        }else {
            return Optional.empty();
        }
    }


}
