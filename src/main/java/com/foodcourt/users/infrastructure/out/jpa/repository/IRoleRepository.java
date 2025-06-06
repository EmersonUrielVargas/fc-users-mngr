package com.foodcourt.users.infrastructure.out.jpa.repository;

import com.foodcourt.users.infrastructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByNombre(String nombre);
}
