package com.foodcourt.users.application.handler.impl;

import com.foodcourt.users.application.dto.request.UserLoginRequestDto;
import com.foodcourt.users.application.dto.response.UserLoginResponseDto;
import com.foodcourt.users.application.handler.IAuthUserHandler;
import com.foodcourt.users.domain.api.IAuthUserServicePort;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthUserHandler implements IAuthUserHandler {

    private final IAuthUserServicePort authUserServicePort;

    @Override
    public UserLoginResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        UserLoginResponseDto responseDto = new UserLoginResponseDto();
        String authToken = authUserServicePort.loginUser(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
        responseDto.setAuthToken(authToken);
        return responseDto;
    }

}
