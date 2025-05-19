package com.foodcourt.users.infrastructure.out.jpa.adapter;

import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @Test
    void saveUser() {
        User user = new User();
        UserEntity userEntity = new UserEntity();

        when(userEntityMapper.toUserEntity(user)).thenReturn(userEntity);
        userJpaAdapter.saveUser(user);

        verify(userEntityMapper).toUserEntity(user);
        verify(userRepository).save(userEntity);
    }
}