package com.foodcourt.users.domain.api;

public interface IAuthUserServicePort {
    String loginUser(String email, String password);
}
