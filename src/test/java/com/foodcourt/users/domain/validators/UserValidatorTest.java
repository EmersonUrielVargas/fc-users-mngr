package com.foodcourt.users.domain.validators;

import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserValidatorTest {

    @Test
    void shouldValidateCorrectEmail(){
        User user = User.builder().email("test@pragma.com").build();
        Assertions.assertDoesNotThrow(()-> UserValidator.validateEmail(user));
    }

    @Test
    void shouldValidateCorrectPhone(){
        User user = User.builder().phoneNumber("+573158000000").build();
        Assertions.assertDoesNotThrow(()-> UserValidator.validatePhone(user));
    }

    @Test
    void shouldValidateCorrectAge(){
        User user = User.builder().birthDate(LocalDate.now().minusYears(19)).build();
        Assertions.assertDoesNotThrow(()-> UserValidator.validateAge(user));
    }

    @Test
    void shouldValidateCorrectIdNumber(){
        User user = User.builder().idNumber("12344234").build();
        Assertions.assertDoesNotThrow(()-> UserValidator.validateIDNumber(user));
    }

    @Test
    void shouldValidateWrongEmail(){
        User user = User.builder().email("test@.com").build();
        DomainException exception = assertThrows(DomainException.class, ()->{
            UserValidator.validateEmail(user);
        });
        assertEquals(Constants.INVALID_EMAIL, exception.getMessage());
    }

    @Test
    void shouldValidateWrongPhone(){
        User user = User.builder().phoneNumber("+573158T").build();
        DomainException exception = assertThrows(DomainException.class, ()->{
            UserValidator.validatePhone(user);
        });
        assertEquals(Constants.INVALID_PHONE_NUMBER, exception.getMessage());
    }

    @Test
    void shouldValidateWrongAge(){
        User user = User.builder().birthDate(LocalDate.now().minusYears(2)).build();
        DomainException exception = assertThrows(DomainException.class, ()->{
            UserValidator.validateAge(user);
        });
        assertEquals(Constants.INVALID_AGE, exception.getMessage());
    }

    @Test
    void shouldValidateWrongIdNumber(){
        User user = User.builder().idNumber("1M234T4234").build();
        DomainException exception = assertThrows(DomainException.class, ()->{
            UserValidator.validateIDNumber(user);
        });
        assertEquals(Constants.INVALID_ID_NUMBER, exception.getMessage());
    }


}
