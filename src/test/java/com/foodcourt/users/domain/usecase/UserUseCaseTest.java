package com.foodcourt.users.domain.usecase;

import com.foodcourt.users.domain.DataDomainFactory;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
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
class UserUseCaseTest {

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
        User userOwner = DataDomainFactory.createUser();
        userOwner.setBirthDate(LocalDate.now().minusYears(19));
        userOwner.setRole(DataDomainFactory.createEmptyRole());

        Role ownerRole = DataDomainFactory.createRole();
        ownerRole.setName(UserRole.OWNER);

        when(rolePersistencePort.getByName(UserRole.OWNER))
                .thenReturn(Optional.of(ownerRole));

        when(passwordEncoderPort.encoder(anyString()))
                .thenReturn(anyString());
        userUseCase.createOwner(userOwner);

        verify(userPersistencePort).saveUser(userOwner);
    }

    @Test
    void shouldCreateOwnerFailThrownDomainException(){
        User userOwner = DataDomainFactory.createUser();
        userOwner.setBirthDate(LocalDate.now().minusYears(19));
        userOwner.setRole(DataDomainFactory.createEmptyRole());


        assertThrows(DomainException.class, () -> userUseCase.createOwner(userOwner));
    }

    @Test
    void shouldReturnUserRoleSuccessful(){
        Long userId = 2L;
        Role ownerRole = DataDomainFactory.createRole();
        ownerRole.setName(UserRole.OWNER);

        User userOwner = new User();
        userOwner.setRole(ownerRole);
        when(userPersistencePort.getUserById(anyLong())).thenReturn(Optional.of(userOwner));

        String roleResponse = userUseCase.getUserRoleById(userId);

        verify(userPersistencePort).getUserById(userId);
        assertEquals(roleResponse, ownerRole.getName().name());

    }

    @Test
    void shouldThrowExceptionUserNotFound(){
        Long userId = 2L;
        when(userPersistencePort.getUserById(anyLong())).thenReturn(Optional.empty());

        DomainException exception = assertThrows(DomainException.class, () -> userUseCase.getUserRoleById(userId));

        assertEquals(Constants.USER_NO_FOUND, exception.getMessage());
        verify(userPersistencePort).getUserById(userId);
    }

    @Test
    void shouldCreateEmployeeSuccessFull(){
        User userEmployee = DataDomainFactory.createUser();
        Role employeeRole = DataDomainFactory.createRole();
        employeeRole.setName(UserRole.EMPLOYEE);
        userEmployee.setRole(employeeRole);

        when(rolePersistencePort.getByName(UserRole.EMPLOYEE))
                .thenReturn(Optional.of(employeeRole));

        when(passwordEncoderPort.encoder(anyString()))
                .thenReturn(anyString());
        userUseCase.createEmployee(userEmployee);

        verify(userPersistencePort).saveUser(any(User.class));
    }

    @Test
    void shouldCreateEmployeeFailThrownDomainException(){
        User userEmployee = DataDomainFactory.createUser();
        userEmployee.setRole(DataDomainFactory.createEmptyRole());

        Role employeeRole = DataDomainFactory.createRole();
        employeeRole.setName(UserRole.EMPLOYEE);

        when(rolePersistencePort.getByName(UserRole.EMPLOYEE))
                .thenReturn(Optional.of(employeeRole));

        assertThrows(DomainException.class, () -> userUseCase.createEmployee(userEmployee));
    }

    @Test
    void shouldCreateClientSuccessFull(){
        User userClient = DataDomainFactory.createUser();

        when(rolePersistencePort.getByName(UserRole.CLIENT))
                .thenReturn(Optional.of(DataDomainFactory.createRole()));
        when(passwordEncoderPort.encoder(anyString()))
                .thenReturn(anyString());

        userUseCase.createClient(userClient);

        verify(userPersistencePort).saveUser(any(User.class));
    }

    @Test
    void shouldCreateClientFailThrownDomainException(){
        User userClient = DataDomainFactory.createUser();
        userClient.setRole(DataDomainFactory.createEmptyRole());

        when(rolePersistencePort.getByName(UserRole.CLIENT))
                .thenReturn(Optional.empty());

        assertThrows(DomainException.class, () -> userUseCase.createClient(userClient));
    }

}
