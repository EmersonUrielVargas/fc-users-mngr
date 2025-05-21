package com.foodcourt.users.infrastructure.security.adapter;

import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IAuthenticationPort;
import com.foodcourt.users.infrastructure.security.JwtService;
import com.foodcourt.users.infrastructure.security.dto.UserDetailsDto;
import com.foodcourt.users.infrastructure.security.mapper.IUserDetailsMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class TokenJwtAdapter implements IAuthenticationPort {

    private final JwtService jwtService;
    private final IUserDetailsMapper userDetailsMapper;

    @Override
    public Optional<String> generateToken(User user) {
        UserDetailsDto userDetailsDto = userDetailsMapper.toUserDetailsDto(user);
        return Optional.of(jwtService.generateToken(userDetailsDto));
    }
}
