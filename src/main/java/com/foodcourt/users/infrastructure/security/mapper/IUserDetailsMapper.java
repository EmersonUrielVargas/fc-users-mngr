package com.foodcourt.users.infrastructure.security.mapper;

import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.infrastructure.out.jpa.entity.UserEntity;
import com.foodcourt.users.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.foodcourt.users.infrastructure.security.dto.UserDetailsDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserDetailsMapper {

    @Mappings({
            @Mapping(source = "role.name", target = "role"),
            @Mapping(source = "id", target = "idUser"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    UserDetailsDto toUserDetailsDto(User user);

    @InheritInverseConfiguration
    User toUser(UserDetailsDto userDetailsDto);
}
