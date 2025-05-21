package com.foodcourt.users.infrastructure.security;

import com.foodcourt.users.domain.spi.IUserPersistencePort;
import com.foodcourt.users.infrastructure.exceptionhandler.ExceptionResponse;
import com.foodcourt.users.infrastructure.security.mapper.IUserDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserPersistencePort userPersistencePort;
    private final IUserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userPersistencePort.getUserByEmail(userEmail)
                .map(userDetailsMapper::toUserDetailsDto)
                .orElseThrow(()-> new UsernameNotFoundException(ExceptionResponse.USER_NOT_FOUND_ERROR.getMessage()));
    }
}
