package com.foodcourt.users.infrastructure.out.jpa.adapter;

import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.spi.IUserPersistencePort;
import com.foodcourt.users.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        return userEntityMapper.toUser(userRepository.save(userEntityMapper.toUserEntity(user)));
    }

    @Override
    public Optional<User> getUserById(Long idUser) {
        Optional<UserEntity> userEntity =  userRepository.findById(idUser);
        return userEntity.map(userEntityMapper::toUser);
    }

    @Override
    public Optional<User> getUserByEmail(String emailUser) {
        Optional<UserEntity> userEntity =  userRepository.findByCorreo(emailUser);
        return userEntity.map(userEntityMapper::toUser);
    }
}
