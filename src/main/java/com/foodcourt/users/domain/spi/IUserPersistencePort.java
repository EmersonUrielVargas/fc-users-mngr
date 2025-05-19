package com.foodcourt.users.domain.spi;

import com.foodcourt.users.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);
}
