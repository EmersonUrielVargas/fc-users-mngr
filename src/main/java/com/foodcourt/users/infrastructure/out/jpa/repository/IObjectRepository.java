package com.foodcourt.users.infrastructure.out.jpa.repository;

import com.foodcourt.users.infrastructure.out.jpa.entity.ObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IObjectRepository extends JpaRepository<ObjectEntity, Long> {

}