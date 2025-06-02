package com.foodcourt.users.domain.api;

import com.foodcourt.users.domain.model.User;

public interface IUserServicePort {
    void createOwner(User user);
    String getUserRoleById(Long idUser);
    void createEmployee(User user);
    void createClient(User user);
}
