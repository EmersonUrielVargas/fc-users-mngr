package com.foodcourt.users.domain.usecase;

import com.foodcourt.users.domain.api.IAuthUserServicePort;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.exception.UserNotFoundException;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IAuthenticationPort;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;


public class AuthUserUseCase implements IAuthUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IAuthenticationPort authenticationPort;


    public AuthUserUseCase(IUserPersistencePort userPersistencePort,
                           IPasswordEncoderPort passwordEncoderPort,
                           IAuthenticationPort authenticationPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.authenticationPort = authenticationPort;
    }

    @Override
    public String loginUser(String email, String password) {
        User userFound = userPersistencePort.getUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        if (passwordEncoderPort.matches(password, userFound.getPassword())){
            return authenticationPort.generateToken(userFound)
                    .orElseThrow(()->new DomainException(Constants.ERROR_GENERATE_TOKEN));
        }else {
            throw new DomainException(Constants.INVALID_CREDENTIALS);
        }
    }


}
