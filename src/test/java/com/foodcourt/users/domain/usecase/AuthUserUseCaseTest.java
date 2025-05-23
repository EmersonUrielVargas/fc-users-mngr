package com.foodcourt.users.domain.usecase;

import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IAuthenticationPort;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthUserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IPasswordEncoderPort passwordEncoderPort;
    @Mock
    private IAuthenticationPort authenticationPort;

    @InjectMocks
    private AuthUserUseCase authUserUseCase;

    @Test
    void loginUserSuccessFull() {
        String userEmail = "test@example.com";
        String userPassword = "password124";
        String tokenGenerated = "generatedToken";
        User userFound = User.builder()
                .email("test@example.com")
                .password("Assurance123")
                .birthDate(LocalDate.now().minusYears(19))
                .idNumber("1234567")
                .name("Jane")
                .phoneNumber("+573158000111")
                .lastName("doe")
                .build();
        Role ownerRole = Role.builder()
                .name(UserRole.OWNER).
                id(1L).
                build();

        when(userPersistencePort.getUserByEmail(userEmail))
                .thenReturn(Optional.of(userFound));

        when(passwordEncoderPort.matches(anyString(), anyString()))
                .thenReturn(true);
        when(authenticationPort.generateToken(userFound))
                .thenReturn(Optional.of(tokenGenerated));
        String token = authUserUseCase.loginUser(userEmail,userPassword);

        verify(authenticationPort).generateToken(any(User.class));
        assertEquals(tokenGenerated, token);
    }

    @Test
    void LoginUserFailCredentials() {
        String userEmail = "test@example.com";
        String userPassword = "password124";
        User userFound = User.builder()
                .email("test@example.com")
                .password("Assurance123")
                .birthDate(LocalDate.now().minusYears(19))
                .idNumber("1234567")
                .name("Jane")
                .phoneNumber("+573158000111")
                .lastName("doe")
                .build();
        when(userPersistencePort.getUserByEmail(userEmail))
                .thenReturn(Optional.of(userFound));

        when(passwordEncoderPort.matches(anyString(), anyString()))
                .thenReturn(false);

        DomainException exception = assertThrows(DomainException.class, () -> authUserUseCase.loginUser(userEmail,userPassword));

        assertEquals(Constants.INVALID_CREDENTIALS, exception.getMessage());
        verify(userPersistencePort).getUserByEmail(userEmail);

    }

    @Test
    void LoginUserNotFound() {
        String userEmail = "test@example.com";
        String userPassword = "password124";
        when(userPersistencePort.getUserByEmail(userEmail))
                .thenReturn(Optional.empty());

        DomainException exception = assertThrows(DomainException.class, () -> authUserUseCase.loginUser(userEmail,userPassword));

        assertEquals(Constants.USER_NO_FOUND, exception.getMessage());
        verify(userPersistencePort).getUserByEmail(userEmail);
    }

    @Test
    void LoginUserErrorGenerateToken() {
        String userEmail = "test@example.com";
        String userPassword = "password124";
        User userFound = User.builder()
                .email("test@example.com")
                .password("Assurance123")
                .birthDate(LocalDate.now().minusYears(19))
                .idNumber("1234567")
                .name("Jane")
                .phoneNumber("+573158000111")
                .lastName("doe")
                .build();
        when(userPersistencePort.getUserByEmail(userEmail))
                .thenReturn(Optional.of(userFound));

        when(passwordEncoderPort.matches(anyString(), anyString()))
                .thenReturn(true);

        when(authenticationPort.generateToken(userFound))
                .thenReturn(Optional.empty());

        DomainException exception = assertThrows(DomainException.class, () -> authUserUseCase.loginUser(userEmail,userPassword));

        assertEquals(Constants.ERROR_GENERATE_TOKEN, exception.getMessage());
        verify(userPersistencePort).getUserByEmail(userEmail);
    }
}