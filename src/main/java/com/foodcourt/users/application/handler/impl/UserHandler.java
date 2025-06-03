package com.foodcourt.users.application.handler.impl;

import com.foodcourt.users.application.dto.request.CreateClientRequestDto;
import com.foodcourt.users.application.dto.request.CreateEmployeeRequestDto;
import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.response.UserContactInfoResponseDto;
import com.foodcourt.users.application.dto.response.UserRoleResponseDto;
import com.foodcourt.users.application.handler.IUserHandler;
import com.foodcourt.users.application.mapper.ICreateUserRequestMapper;
import com.foodcourt.users.domain.api.IUserServicePort;
import com.foodcourt.users.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final ICreateUserRequestMapper userRequestMapper;

    @Override
    public void createOwner(OwnerRequestDto owner) {
        User userToCreate = userRequestMapper.toUser(owner);
        userServicePort.createOwner(userToCreate);
    }

    @Override
    public UserRoleResponseDto getUserRoleById(Long id) {
        UserRoleResponseDto response = new UserRoleResponseDto();
        String roleFound = userServicePort.getUserRoleById(id);
        response.setUserRole(roleFound);
        return response;
    }

    @Override
    public void createEmployee(CreateEmployeeRequestDto createEmployeeRequestDto) {
        User employee = userRequestMapper.toUser(createEmployeeRequestDto);
        userServicePort.createEmployee(employee);
    }

    @Override
    public void createClient(CreateClientRequestDto clientRequestDto) {
        User userClient = userRequestMapper.toUser(clientRequestDto);
        userServicePort.createClient(userClient);
    }

    @Override
    public UserContactInfoResponseDto getContactInfoUser(Long id) {
        User domainUser = userServicePort.findById(id);
        return userRequestMapper.toUserContactInfoResponseDto(domainUser);
    }


}
