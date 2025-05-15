package com.foodcourt.users.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OwnerRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String idNumber;
    @NotNull
    private String phoneNumber;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
