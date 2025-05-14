package com.foodcourt.users.domain.spi;

import com.foodcourt.users.domain.model.User;
import reactor.core.publisher.Mono;

public interface IUserPersistencePort {
    Mono<Void> saveUser(User user);
}
