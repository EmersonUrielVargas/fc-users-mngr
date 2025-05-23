package com.foodcourt.users.application.mapper;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOwnerRequestMapper {

    User toUser(OwnerRequestDto owner);
}
