package com.foodcourt.users.infrastructure.out.jpa.mapper;

import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {

    @Mappings({
            @Mapping(source = "name", target = "nombre"),
            @Mapping(source = "lastName", target = "apellido"),
            @Mapping(source = "idNumber", target = "numeroDocumento"),
            @Mapping(source = "phoneNumber", target = "celular"),
            @Mapping(source = "birthDate", target = "fechaNacimiento"),
            @Mapping(source = "email", target = "correo"),
            @Mapping(source = "password", target = "clave")
    })
    UserEntity toUserEntity(User user);

    @InheritInverseConfiguration
    User toUser(UserEntity userEntity);
}
