package com.foodcourt.users.infrastructure.input.rest;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mngr/users")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Create a new Owner user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
    })
    @PostMapping("/owner")
    public ResponseEntity<Void> saveOwner(@RequestBody OwnerRequestDto ownerRequestDto) {
        userHandler.createOwner(ownerRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
