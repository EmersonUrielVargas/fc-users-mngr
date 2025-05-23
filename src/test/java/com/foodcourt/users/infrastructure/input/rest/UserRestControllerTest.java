package com.foodcourt.users.infrastructure.input.rest;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.response.UserRoleResponseDto;
import com.foodcourt.users.application.handler.IUserHandler;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.enums.UserRole;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    @InjectMocks
    private UserRestController userRestController;

    @Mock
    private IUserHandler userHandler;

    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController)
                .setControllerAdvice(new ControllerAdvisor())
                .build();
    }

    @Test
    void createOwnerSuccessful() throws Exception {
        String jsonBody = """
            {
              "name": "Juan",
              "lastName": "Pérez",
              "idNumber": "123456789",
              "phoneNumber": "3001234567",
              "birthDate": "1990-01-01",
              "email": "juan.perez@example.com",
              "password": "12345"
            }
        """;

        doNothing().when(userHandler).createOwner(any(OwnerRequestDto.class));
        MockHttpServletRequestBuilder request = post("/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void createOwnerFail() throws Exception {
        String jsonBody = """
            {
              "name": "Juan",
              "lastName": "Pérez",
              "idNumber": "123456789",
              "phoneNumber": "3001234567",
              "birthDate": "1990-01-01",
              "email": "juan.perez@example.com",
              "password": "12345"
            }
        """;
        doThrow(new DomainException("fail data")).when(userHandler).createOwner(any(OwnerRequestDto.class));
        MockHttpServletRequestBuilder request = post("/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void createOwnerInvalidRequest() throws Exception {
        String jsonBody = """
            {
              "name": "Juan",
              "lastName": "Pérez",
              "idNumber": "123456789",
              "phoneNumber": "3001234567",
              "birthDate": "1990-01-01",
              "email": "juan.perez@example.com"
            }
        """;

        MockHttpServletRequestBuilder request = post("/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUSerRolByIdShouldSuccessful() throws Exception {
        Long userId = 3L;
        UserRole role = UserRole.OWNER;

        UserRoleResponseDto userRoleResponse = new UserRoleResponseDto();
        userRoleResponse.setUserRole(role.toString());
        String jsonResponse = """
                {
                    "userRole":"OWNER"
                }
                """;

        when(userHandler.getUserRoleById(anyLong())).thenReturn(userRoleResponse);

        MockHttpServletRequestBuilder request = get("/role/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void getUserRoleShouldReturnConflict() throws Exception {
        Long userId = 3L;
        when(userHandler.getUserRoleById(userId))
                .thenThrow(new DomainException(Constants.ROLE_NO_FOUND));

        MockHttpServletRequestBuilder request = get("/role/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
