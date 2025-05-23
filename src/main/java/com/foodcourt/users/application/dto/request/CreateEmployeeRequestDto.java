package com.foodcourt.users.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String idNumber;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Long idRole;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
