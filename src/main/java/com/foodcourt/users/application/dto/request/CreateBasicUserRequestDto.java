package com.foodcourt.users.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBasicUserRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String idNumber;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
