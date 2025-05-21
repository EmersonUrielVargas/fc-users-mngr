package com.foodcourt.users.infrastructure.input.rest;

import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.request.UserLoginRequestDto;
import com.foodcourt.users.application.dto.response.UserLoginResponseDto;
import com.foodcourt.users.application.dto.response.UserRoleResponseDto;
import com.foodcourt.users.application.handler.IAuthUserHandler;
import com.foodcourt.users.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final IAuthUserHandler authUserHandler;

    @Operation(summary = "User Login with credentials (email, password)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful return token", content = @Content),
            @ApiResponse(responseCode = "409", description = "User not found", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> loginUser(@Valid @RequestBody UserLoginRequestDto loginRequestDto) {
        UserLoginResponseDto authResponse = authUserHandler.loginUser(loginRequestDto);
        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

}
