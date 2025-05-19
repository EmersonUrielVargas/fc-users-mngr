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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Test
    void getUserById() {
        Long userId = 3L;
        User user = User.builder().name("roger").build();
        UserEntity userEntity = UserEntity.builder().nombre("roger").build();;

        when(userEntityMapper.toUser(userEntity)).thenReturn(user);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        Optional<User> userFound = userJpaAdapter.getUserById(userId);

        verify(userEntityMapper).toUser(userEntity);
        verify(userRepository).findById(userId);
        assertEquals(Optional.of(user), userFound);
    }
}