package com.foodcourt.users.infrastructure.out.jpa.repository;

import com.foodcourt.users.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByCorreo(String userEmail);
}
