package com.foodcourt.users.infrastructure.configuration;

import com.foodcourt.users.domain.api.IObjectServicePort;
import com.foodcourt.users.infrastructure.out.jpa.adapter.ObjectJpaAdapter;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.repository.IObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IObjectRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;

    @Bean
    public IObjectPersistencePort objectPersistencePort() {
        return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
    }

    @Bean
    public IObjectServicePort objectServicePort() {
        return new ObjectUseCase(objectPersistencePort());
    }
}