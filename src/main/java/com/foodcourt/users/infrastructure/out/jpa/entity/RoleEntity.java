package com.foodcourt.users.infrastructure.out.jpa.entity;

import com.foodcourt.users.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false)
    private Long id;

    private String nombre;

    private String descripcion;
}
