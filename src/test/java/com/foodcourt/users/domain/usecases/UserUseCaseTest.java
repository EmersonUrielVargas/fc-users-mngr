package com.foodcourt.users.domain.usecases;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
import com.foodcourt.users.domain.usecase.UserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IPasswordEncoderPort passwordEncoderPort;
    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    void shouldCreateOwnerSuccessFull(){
        User userOwner = User.builder()
                .email("test@pragma.com")
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

        when(rolePersistencePort.getByName(UserRole.OWNER))
                .thenReturn(Optional.of(ownerRole));

        when(passwordEncoderPort.encoder(anyString()))
                .thenReturn(anyString());
        userUseCase.createOwner(userOwner);

        verify(userPersistencePort).saveUser(userOwner);
    }

    @Test
    void shouldCreateOwnerFailThrownDomainException(){
        User userOwner = User.builder()
                .email("test@.com")
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

        assertThrows(DomainException.class, () -> userUseCase.createOwner(userOwner));
    }
}
