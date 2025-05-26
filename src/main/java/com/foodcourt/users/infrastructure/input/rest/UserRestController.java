package com.foodcourt.users.infrastructure.input.rest;

import com.foodcourt.users.application.dto.request.CreateClientRequestDto;
import com.foodcourt.users.application.dto.request.CreateEmployeeRequestDto;
import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.response.UserRoleResponseDto;
import com.foodcourt.users.application.handler.IUserHandler;
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
@RequestMapping("/")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = Constants.SUMMARY_SAVE_OWNER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.STATUS_CODE_CREATED, description = Constants.SUMMARY_RESPONSE_CREATED_SAVE_OWNER),
            @ApiResponse(responseCode = Constants.STATUS_CODE_CONFLICT, description = Constants.SUMMARY_RESPONSE_CONFLICT_SAVE_OWNER, content = @Content)
    })
    @PostMapping("/owner")
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody OwnerRequestDto ownerRequestDto) {
        userHandler.createOwner(ownerRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = Constants.SUMMARY_GET_ROLE_BY_USER_ID)
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.STATUS_CODE_OK, description = Constants.SUMMARY_RESPONSE_OK_GET_ROLE,
                    content = @Content(mediaType = Constants.MEDIA_TYPE_JSON,
                        schema = @Schema(implementation = UserRoleResponseDto.class)
                    )
            ),
            @ApiResponse(responseCode = Constants.STATUS_CODE_CONFLICT, description = Constants.SUMMARY_RESPONSE_CONFLICT_GET_ROLE, content = @Content)
    })
    @GetMapping("/role/{id}")
    public ResponseEntity<UserRoleResponseDto> getUserRole(@Valid @PathVariable Long id) {
        UserRoleResponseDto response =  userHandler.getUserRoleById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @Operation(summary = Constants.SUMMARY_SAVE_EMPLOYEE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.STATUS_CODE_CREATED, description = Constants.SUMMARY_RESPONSE_CREATED_SAVE_EMPLOYEE),
            @ApiResponse(responseCode = Constants.STATUS_CODE_CONFLICT, description = Constants.SUMMARY_RESPONSE_CONFLICT_SAVE_EMPLOYEE, content = @Content)
    })
    @PostMapping("/employee")
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody CreateEmployeeRequestDto employeeRequestDto) {
        userHandler.createEmployee(employeeRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = Constants.SUMMARY_SAVE_CLIENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.STATUS_CODE_CREATED, description = Constants.SUMMARY_RESPONSE_CREATED_SAVE_CLIENT, content = @Content),
            @ApiResponse(responseCode = Constants.STATUS_CODE_CONFLICT, description = Constants.SUMMARY_RESPONSE_CONFLICT_SAVE_CLIENT, content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<Void> saveClient(@Valid @RequestBody CreateClientRequestDto clientRequestDto) {
        userHandler.createClient(clientRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
