package com.foodcourt.users.infrastructure.out.jpa.adapter;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.domain.spi.IRolePersistencePort;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.foodcourt.users.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;


    @Override
    public Optional<Role> getByName(UserRole roleName) {
        String rolNameBd = IRoleEntityMapper.mapUserRoleToString(roleName);
        return roleRepository.findByNombre(rolNameBd)
                .map(roleEntityMapper::toRole);
    }

    @Override
    public Optional<Role> getById(Long idRole) {
        return roleRepository.findById(idRole).map(roleEntityMapper::toRole);
    }
}
