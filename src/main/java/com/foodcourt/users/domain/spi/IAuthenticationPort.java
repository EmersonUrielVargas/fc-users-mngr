package com.foodcourt.users.domain.spi;

import com.foodcourt.users.domain.model.User;

import java.util.Optional;

public interface IAuthenticationPort {
    Optional<String> generateToken(User user);
}
