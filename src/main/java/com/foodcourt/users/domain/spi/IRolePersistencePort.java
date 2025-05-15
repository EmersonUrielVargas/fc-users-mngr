package com.foodcourt.users.domain.spi;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> getByName(UserRole roleName);
    Optional<Role> getById(Long idRole);
}
