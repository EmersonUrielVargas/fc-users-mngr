package com.foodcourt.users.application.handler.impl;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.response.UserRoleResponseDto;
import com.foodcourt.users.application.mapper.IOwnerRequestMapper;
import com.foodcourt.users.domain.api.IUserServicePort;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserHandlerTest {

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IOwnerRequestMapper ownerMapper;

    @InjectMocks
    private UserHandler userHandler;


    @Test
    void createOwnerSuccessful() {
        OwnerRequestDto ownerRequestDto = new OwnerRequestDto();
        ownerRequestDto.setName("Clift");
        ownerRequestDto.setLastName("Jones");
        ownerRequestDto.setIdNumber("123456789");
        ownerRequestDto.setPhoneNumber("+1234567890");
        ownerRequestDto.setBirthDate(LocalDate.of(1990, 1, 1));
        ownerRequestDto.setEmail("clift.jones@example.com");
        ownerRequestDto.setPassword("password123");

        User user = new User();
        user.setName("Diana");
        user.setLastName("Barrigan");
        user.setIdNumber("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setEmail("diana.barrigan@example.com");
        user.setPassword("password123");
        when(ownerMapper.toUser(ownerRequestDto)).thenReturn(user);
        userHandler.createOwner(ownerRequestDto);

        verify(ownerMapper, times(1)).toUser(ownerRequestDto);
        verify(userServicePort, times(1)).createOwner(user);
    }

    @Test
    void getUserRoleById() {
        Long userId = 1L;
        UserRole expectedRoleEnum = UserRole.OWNER;

        when(userServicePort.getUserRoleById(userId)).thenReturn(Optional.of(expectedRoleEnum));

        UserRoleResponseDto actualResponse = userHandler.getUserRoleById(userId);

        assertNotNull(actualResponse);
        assertEquals(expectedRoleEnum.toString(), actualResponse.getUserRole());
        verify(userServicePort, times(1)).getUserRoleById(userId);
    }

    @Test
    void getUserRoleByIdShouldFail() {
        Long userId = 2L;
        when(userServicePort.getUserRoleById(userId)).thenReturn(Optional.empty());

        DomainException exception = assertThrows(DomainException.class, () -> {
            userHandler.getUserRoleById(userId);
        });

        assertEquals(Constants.ROLE_NO_FOUND, exception.getMessage());
        verify(userServicePort, times(1)).getUserRoleById(userId);
    }
}