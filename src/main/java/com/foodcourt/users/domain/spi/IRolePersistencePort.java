package com.foodcourt.users.domain.spi;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.Role;
import reactor.core.publisher.Mono;

public interface IRolePersistencePort {
    Mono<Role> getByName(UserRole roleName);
    Mono<Role> getById(Long idRole);
}
