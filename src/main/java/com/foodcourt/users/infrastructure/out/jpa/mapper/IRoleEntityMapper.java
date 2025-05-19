package com.foodcourt.users.infrastructure.out.jpa.mapper;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.infrastructure.exceptionhandler.ExceptionResponse;
import com.foodcourt.users.infrastructure.out.jpa.entity.RoleEntity;
import org.hibernate.boot.model.relational.QualifiedName;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRoleEntityMapper {

    @Mappings({
            @Mapping(source = "name", target = "nombre", qualifiedByName = "mapUserRoleToString"),
            @Mapping(source = "description", target = "descripcion")
    })
    RoleEntity toRoleEntity(Role role);

    @InheritInverseConfiguration
    @Mapping(source = "nombre", target = "name", qualifiedByName = "mapStringToUserRole")
    Role toRole(RoleEntity roleEntity);

    @Named("mapUserRoleToString")
    static String mapUserRoleToString(UserRole userRole) {
        return switch (userRole) {
            case ADMIN -> "Administrador";
            case OWNER -> "Propietario";
            case EMPLOYEE -> "Empleado";
            case CLIENT -> "Cliente";
        };
    }

    @Named("mapStringToUserRole")
    static UserRole mapStringToUserRole(String roleNameDb) {
        return switch (roleNameDb.trim().toUpperCase()) {
            case "ADMINISTRADOR" -> UserRole.ADMIN;
            case "PROPIETARIO" -> UserRole.OWNER;
            case "EMPLEADO" -> UserRole.EMPLOYEE;
            case "CLIENTE" -> UserRole.CLIENT;
            default -> throw new IllegalArgumentException(ExceptionResponse.NO_DATA_FOUND.getMessage());
        };
    }

}
