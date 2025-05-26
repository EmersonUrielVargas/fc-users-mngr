package com.foodcourt.users.infrastructure.configuration;

import com.foodcourt.users.domain.api.IAuthUserServicePort;
import com.foodcourt.users.domain.api.IUserServicePort;
import com.foodcourt.users.domain.spi.IAuthenticationPort;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
import com.foodcourt.users.domain.usecase.AuthUserUseCase;
import com.foodcourt.users.domain.usecase.UserUseCase;
import com.foodcourt.users.infrastructure.security.JwtAuthenticationFilter;
import com.foodcourt.users.infrastructure.security.service.JwtService;
import com.foodcourt.users.infrastructure.security.service.UserDetailsServiceImpl;
import com.foodcourt.users.infrastructure.security.adapter.PasswordEncoderAdapter;
import com.foodcourt.users.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.foodcourt.users.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.repository.IRoleRepository;
import com.foodcourt.users.infrastructure.out.jpa.repository.IUserRepository;
import com.foodcourt.users.infrastructure.security.adapter.TokenJwtAdapter;
import com.foodcourt.users.infrastructure.security.mapper.IUserDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Value("${application.security.jwt.rounds-encrypt:12}")
    private Integer rountsToken;

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final JwtService jwtService;
    private final IUserDetailsMapper userDetailsMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort() {
        return new PasswordEncoderAdapter(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(rountsToken);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), passwordEncoderPort(), rolePersistencePort());
    }

    @Bean
    public IAuthUserServicePort authUserServicePort() {
        return new AuthUserUseCase(userPersistencePort(), passwordEncoderPort(), authenticationPort());
    }

    @Bean
    public IAuthenticationPort authenticationPort(){
        return new TokenJwtAdapter(jwtService, userDetailsMapper);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl(userPersistencePort(), userDetailsMapper);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtService, userDetailsService());
    }

}