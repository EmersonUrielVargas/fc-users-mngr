package com.foodcourt.users.application.handler.impl;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.response.UserRoleResponseDto;
import com.foodcourt.users.application.handler.IUserHandler;
import com.foodcourt.users.application.mapper.IOwnerRequestMapper;
import com.foodcourt.users.domain.api.IUserServicePort;
import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IOwnerRequestMapper ownerRequestMapper;

    @Override
    public void createOwner(OwnerRequestDto owner) {
        User userToCreate = ownerRequestMapper.toUser(owner);
        userServicePort.createOwner(userToCreate);
    }

    @Override
    public UserRoleResponseDto getUserRoleById(Long id) {
        UserRoleResponseDto response = new UserRoleResponseDto();
        UserRole roleFound = userServicePort.getUserRoleById(id)
                .orElseThrow(()-> new DomainException(Constants.ROLE_NO_FOUND));
        response.setUserRole(roleFound.toString());
        return response;
    }


}
