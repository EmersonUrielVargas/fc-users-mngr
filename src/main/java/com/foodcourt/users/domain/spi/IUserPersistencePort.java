package com.foodcourt.users.domain.spi;

import com.foodcourt.users.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveUser(User user);

    Optional<User> getUserById(Long idUser);
}
