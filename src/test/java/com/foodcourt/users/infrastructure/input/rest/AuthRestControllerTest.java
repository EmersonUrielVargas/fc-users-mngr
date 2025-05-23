package com.foodcourt.users.infrastructure.input.rest;

import com.foodcourt.users.application.dto.request.UserLoginRequestDto;
import com.foodcourt.users.application.dto.response.UserLoginResponseDto;
import com.foodcourt.users.application.handler.IAuthUserHandler;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.infrastructure.exceptionhandler.ControllerAdvisor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthRestControllerTest {

    @InjectMocks
    private AuthRestController authRestController;

    @Mock
    private IAuthUserHandler authUserHandler;

    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(authRestController)
                .setControllerAdvice(new ControllerAdvisor())
                .build();
    }

    @Test
    void loginSuccessful() throws Exception {
        String jsonBody = """
            {
              "email": "juan.perez@example.com",
              "password": "12345"
            }
        """;

        UserLoginResponseDto userLogin = new UserLoginResponseDto();
        userLogin.setAuthToken("tokenGet");
        when(authUserHandler.loginUser(any(UserLoginRequestDto.class))).thenReturn(userLogin);
        MockHttpServletRequestBuilder request = post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void loginCredentialsFail() throws Exception {
        String jsonBody = """
            {
              "email": "juan.perez@example.com",
              "password": "12345"
            }
        """;
        when(authUserHandler.loginUser(any(UserLoginRequestDto.class)))
                .thenThrow(new DomainException(Constants.INVALID_CREDENTIALS));
        MockHttpServletRequestBuilder request = post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void loginBadRequestFail() throws Exception {
        String jsonBody = """
            {
              "email": "juan.perez@example.com"
            }
        """;

        MockHttpServletRequestBuilder request = post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}