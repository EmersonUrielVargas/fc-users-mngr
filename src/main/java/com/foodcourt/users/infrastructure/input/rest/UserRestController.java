package com.foodcourt.users.infrastructure.input.rest;

import com.foodcourt.users.application.dto.request.CreateEmployeeRequestDto;
import com.foodcourt.users.application.dto.request.OwnerRequestDto;
import com.foodcourt.users.application.dto.response.UserRoleResponseDto;
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
@RequestMapping("/")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Create a new Owner user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
    })
    @PostMapping("/owner")
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody OwnerRequestDto ownerRequestDto) {
        userHandler.createOwner(ownerRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get UserRole by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Found", content = @Content),
            @ApiResponse(responseCode = "409", description = "User not found", content = @Content)
    })
    @GetMapping("/role/{id}")
    public ResponseEntity<UserRoleResponseDto> getUserRole(@Valid @PathVariable Long id) {
        UserRoleResponseDto response =  userHandler.getUserRoleById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(summary = "Create a new employee user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "employee information is invalid", content = @Content)
    })
    @PostMapping("/employee")
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody CreateEmployeeRequestDto employeeRequestDto) {
        userHandler.createEmployee(employeeRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
