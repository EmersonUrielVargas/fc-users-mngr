package com.foodcourt.users.infrastructure.security.adapter;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.infrastructure.out.jpa.entity.RoleEntity;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.foodcourt.users.infrastructure.security.dto.UserDetailsDto;
import com.foodcourt.users.infrastructure.security.mapper.IUserDetailsMapper;
import com.foodcourt.users.infrastructure.security.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenJwtAdapterTest {

    @Mock
    private JwtService jwtService;
    @Mock
    private IUserDetailsMapper userDetailsMapper;

    @InjectMocks
    private TokenJwtAdapter tokenJwtAdapter;

    @Test
    void generateToken() {
        String token = "token";
        User user = User.builder().name("roger").build();
        UserDetailsDto userDetailsDto = UserDetailsDto.builder().build();

        when(jwtService.generateToken(userDetailsDto)).thenReturn(token);
        when(userDetailsMapper.toUserDetailsDto(user)).thenReturn(userDetailsDto);

        Optional<String> tokenGenerated = tokenJwtAdapter.generateToken(user);

        assertTrue(tokenGenerated.isPresent());
        verify(jwtService).generateToken(any(UserDetailsDto.class));
        verify(userDetailsMapper).toUserDetailsDto(any(User.class));
    }
}