package com.foodcourt.users.infrastructure.input.rest;

import com.foodcourt.users.application.dto.request.UserLoginRequestDto;
import com.foodcourt.users.application.dto.response.UserLoginResponseDto;
import com.foodcourt.users.application.handler.IAuthUserHandler;
import com.foodcourt.users.infrastructure.shared.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = Constants.SUMMARY_LOGIN_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.STATUS_CODE_OK, description = Constants.SUMMARY_RESPONSE_OK_LOGIN_USER,
                    content = @Content(mediaType = Constants.MEDIA_TYPE_JSON,
                        schema = @Schema(implementation = UserLoginResponseDto.class)
                    )),
            @ApiResponse(responseCode = Constants.STATUS_CODE_CONFLICT, description = Constants.SUMMARY_RESPONSE_CONFLICT_LOGIN_USER, content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> loginUser(@Valid @RequestBody UserLoginRequestDto loginRequestDto) {
        UserLoginResponseDto authResponse = authUserHandler.loginUser(loginRequestDto);
        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

}
