package com.foodcourt.users.domain.api;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.User;

import java.util.Optional;

public interface IUserServicePort {
    void createOwner(User user);
    Optional<UserRole> getUserRoleById(Long idUser);
    Optional<String> loginUser(String email, String password);
}
