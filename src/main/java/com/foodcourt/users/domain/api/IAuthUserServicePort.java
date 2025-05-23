package com.foodcourt.users.domain.api;

import java.util.Optional;

public interface IAuthUserServicePort {
    String loginUser(String email, String password);
}
