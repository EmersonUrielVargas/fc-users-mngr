package com.foodcourt.users.domain.api;

import com.foodcourt.users.domain.model.User;

public interface IUserServicePort {
    void createOwner(User user);
}
