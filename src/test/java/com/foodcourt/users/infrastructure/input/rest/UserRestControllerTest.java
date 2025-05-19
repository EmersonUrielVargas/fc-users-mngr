package com.foodcourt.users.infrastructure.input.rest;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.handler.IUserHandler;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        MockHttpServletRequestBuilder request = post("/api/v1/mngr/users/owner")
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
        MockHttpServletRequestBuilder request = post("/api/v1/mngr/users/owner")
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

        MockHttpServletRequestBuilder request = post("/api/v1/mngr/users/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
