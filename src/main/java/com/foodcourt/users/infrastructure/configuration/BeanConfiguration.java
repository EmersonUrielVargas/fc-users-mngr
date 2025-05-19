package com.foodcourt.users.infrastructure.configuration;

import com.foodcourt.users.domain.api.IUserServicePort;
import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
import com.foodcourt.users.domain.usecase.UserUseCase;
import com.foodcourt.users.infrastructure.out.jpa.adapter.PasswordEncoderAdapter;
import com.foodcourt.users.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.foodcourt.users.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.repository.IRoleRepository;
import com.foodcourt.users.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

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
        return new PasswordEncoderAdapter(bCryptPasswordEncoder);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), passwordEncoderPort(), rolePersistencePort());
    }
}