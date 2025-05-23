package com.foodcourt.users.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false)
    private Long id;

    private String nombre;

    private String apellido;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    private String celular;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String correo;

    private String clave;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private RoleEntity role;
}
