package com.foodcourt.users.application.handler;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import reactor.core.publisher.Mono;

public interface IUserHandler {

    Mono<Void> createOwner(OwnerRequestDto owner);
}
