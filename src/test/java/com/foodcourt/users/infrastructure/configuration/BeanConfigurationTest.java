package com.foodcourt.users.infrastructure.configuration;

import com.foodcourt.users.domain.api.IAuthUserServicePort;
import com.foodcourt.users.domain.api.IUserServicePort;
import com.foodcourt.users.domain.spi.IAuthenticationPort;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
import com.foodcourt.users.domain.usecase.AuthUserUseCase;
import com.foodcourt.users.domain.usecase.UserUseCase;
import com.foodcourt.users.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.foodcourt.users.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.repository.IRoleRepository;
import com.foodcourt.users.infrastructure.out.jpa.repository.IUserRepository;
import com.foodcourt.users.infrastructure.security.JwtAuthenticationFilter;
import com.foodcourt.users.infrastructure.security.adapter.PasswordEncoderAdapter;
import com.foodcourt.users.infrastructure.security.adapter.TokenJwtAdapter;
import com.foodcourt.users.infrastructure.security.mapper.IUserDetailsMapper;
import com.foodcourt.users.infrastructure.security.service.JwtService;
import com.foodcourt.users.infrastructure.security.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BeanConfigurationTest {

    @Mock
    private IUserRepository userRepository;
    @Mock
    private IUserEntityMapper userEntityMapper;
    @Mock
    private  IRoleRepository roleRepository;
    @Mock
    private IRoleEntityMapper roleEntityMapper;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private IUserDetailsMapper userDetailsMapper;

    @InjectMocks
    private BeanConfiguration beanConfiguration;

    @Test
    void userPersistencePort() {
        IUserPersistencePort userPersistencePort = beanConfiguration.userPersistencePort();

        assertNotNull(userPersistencePort);
        assertInstanceOf(UserJpaAdapter.class, userPersistencePort);
    }

    @Test
    void rolePersistencePort() {
        IRolePersistencePort rolePersistencePort = beanConfiguration.rolePersistencePort();

        assertNotNull(rolePersistencePort);
        assertInstanceOf(RoleJpaAdapter.class, rolePersistencePort);
    }

    @Test
    void passwordEncoderPort() {
        IPasswordEncoderPort passwordEncoderPort = beanConfiguration.passwordEncoderPort();

        assertNotNull(passwordEncoderPort);
        assertInstanceOf(PasswordEncoderAdapter.class, passwordEncoderPort);
    }

    @Test
    void userServicePort() {
        IUserServicePort userServicePort = beanConfiguration.userServicePort();

        assertNotNull(userServicePort);
        assertInstanceOf(UserUseCase.class, userServicePort);
    }

    @Test
    void authUserServicePort() {
        IAuthUserServicePort authUserServicePort = beanConfiguration.authUserServicePort();

        assertNotNull(authUserServicePort);
        assertInstanceOf(AuthUserUseCase.class, authUserServicePort);
    }

    @Test
    void authenticationPort() {
        IAuthenticationPort authenticationPort = beanConfiguration.authenticationPort();

        assertNotNull(authenticationPort);
        assertInstanceOf(TokenJwtAdapter.class, authenticationPort);
    }

    @Test
    void userDetailsService() {
        UserDetailsService userDetailsService = beanConfiguration.userDetailsService();

        assertNotNull(userDetailsService);
        assertInstanceOf(UserDetailsServiceImpl.class, userDetailsService);
    }

    @Test
    void jwtAuthenticationFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = beanConfiguration.jwtAuthenticationFilter();

        assertNotNull(jwtAuthenticationFilter);
        assertInstanceOf(JwtAuthenticationFilter.class, jwtAuthenticationFilter);
    }
}