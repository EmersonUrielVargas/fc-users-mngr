package com.foodcourt.users.domain.api;

import com.foodcourt.users.domain.model.User;
import reactor.core.publisher.Mono;

public interface IUserServicePort {
    Mono<Void> createOwner(User user);
}
