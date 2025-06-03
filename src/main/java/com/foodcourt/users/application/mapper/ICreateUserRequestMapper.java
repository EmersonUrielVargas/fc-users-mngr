package com.foodcourt.users.application.mapper;

import com.foodcourt.users.application.dto.request.CreateClientRequestDto;
import com.foodcourt.users.application.dto.request.CreateEmployeeRequestDto;
import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.response.UserContactInfoResponseDto;
import com.foodcourt.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICreateUserRequestMapper {

    User toUser(OwnerRequestDto owner);

    @Mapping(source = "idRole", target = "role.id")
    User toUser(CreateEmployeeRequestDto employeeRequestDto);

    @Mapping(source = "idRole", target = "role.id")
    User toUser(CreateClientRequestDto employeeRequestDto);

    UserContactInfoResponseDto toUserContactInfoResponseDto(User user);
}
