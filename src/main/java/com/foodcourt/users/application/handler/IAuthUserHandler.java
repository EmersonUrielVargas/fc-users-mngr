package com.foodcourt.users.application.handler;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.request.UserLoginRequestDto;
import com.foodcourt.users.application.dto.response.UserLoginResponseDto;
import com.foodcourt.users.application.dto.response.UserRoleResponseDto;

public interface IAuthUserHandler {

    UserLoginResponseDto loginUser(UserLoginRequestDto userLoginRequestDto);
}
