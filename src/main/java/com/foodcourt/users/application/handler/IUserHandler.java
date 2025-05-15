package com.foodcourt.users.application.handler;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;

public interface IUserHandler {

    void createOwner(OwnerRequestDto owner);
}
