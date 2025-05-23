package com.foodcourt.users.application.handler.impl;

import com.foodcourt.users.application.dto.request.UserLoginRequestDto;
import com.foodcourt.users.application.dto.response.UserLoginResponseDto;
import com.foodcourt.users.domain.api.IAuthUserServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthUserHandlerTest {

    @Mock
    private IAuthUserServicePort authUserServicePort;

    @InjectMocks
    private AuthUserHandler authUserHandler;

    @Test
    void loginUserResponseWithAuthToken() {
        // Arrange
        String userEmail = "test@example.com";
        String password = "password123";
        String expectedAuthToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

        UserLoginRequestDto requestDto = new UserLoginRequestDto();
        requestDto.setEmail(userEmail);
        requestDto.setPassword(password);

        when(authUserServicePort.loginUser(userEmail, password)).thenReturn(expectedAuthToken);

        UserLoginResponseDto actualResponse = authUserHandler.loginUser(requestDto);

        assertNotNull(actualResponse);
        assertEquals(expectedAuthToken, actualResponse.getAuthToken());
        verify(authUserServicePort, times(1)).loginUser(userEmail, password);
    }

    @Test
    void loginUserResponseWithNullToken() {
        String userEmail = "another@example.com";
        String password = "securepassword";

        UserLoginRequestDto requestDto = new UserLoginRequestDto();
        requestDto.setEmail(userEmail);
        requestDto.setPassword(password);

        when(authUserServicePort.loginUser(userEmail, password)).thenReturn(null);

        UserLoginResponseDto actualResponse = authUserHandler.loginUser(requestDto);

        assertNotNull(actualResponse);
        assertNull(actualResponse.getAuthToken());

        verify(authUserServicePort, times(1)).loginUser(userEmail, password);
    }

}