package com.foodcourt.users.infrastructure.exceptionhandler;

import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ControllerAdvisorTest {

    private ControllerAdvisor controllerAdvisor;

    @BeforeEach
    void setUp() {
        controllerAdvisor = new ControllerAdvisor();
    }


    @Test
    void handleNoDataFoundException() {
        NoDataFoundException exception = new NoDataFoundException();

        ResponseEntity<Map<String, String>> responseEntity =
                controllerAdvisor.handleNoDataFoundException(exception);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        Map<String, String> body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(ExceptionResponse.NO_DATA_FOUND.getMessage(), body.get("message"));
    }

    @Test
    void handleDomainException() {
        DomainException exception = new DomainException(Constants.INVALID_CREDENTIALS);

        ResponseEntity<Map<String, String>> responseEntity =
                controllerAdvisor.handleDomainException(exception);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

        Map<String, String> body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(Constants.INVALID_CREDENTIALS, body.get("message"));
    }

    @Test
    void handleInvalidParamException() {
        BindingResult bindingResultMock = mock(BindingResult.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResultMock);

        ResponseEntity<Map<String, String>> responseEntity =
                controllerAdvisor.handleInvalidParamException(exception);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        Map<String, String> body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(ExceptionResponse.DATABASE_ERROR.getMessage(), body.get("message"));
    }

    @Test
    void handleGeneralException() {
        Exception exception = new Exception();

        ResponseEntity<Map<String, String>> responseEntity =
                controllerAdvisor.handleGeneralException(exception);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        Map<String, String> body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(ExceptionResponse.GENERAL_ERROR.getMessage(), body.get("message"));
    }
}