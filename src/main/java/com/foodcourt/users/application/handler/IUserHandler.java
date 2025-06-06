package com.foodcourt.users.application.handler;

import com.foodcourt.users.application.dto.request.CreateClientRequestDto;
import com.foodcourt.users.application.dto.request.CreateEmployeeRequestDto;
import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.response.UserContactInfoResponseDto;
import com.foodcourt.users.application.dto.response.UserRoleResponseDto;

public interface IUserHandler {

    void createOwner(OwnerRequestDto owner);
    UserRoleResponseDto getUserRoleById(Long id);
    void createEmployee(CreateEmployeeRequestDto employeeRequestDto);
    void createClient(CreateClientRequestDto clientRequestDto);
    UserContactInfoResponseDto getContactInfoUser(Long id);


}
