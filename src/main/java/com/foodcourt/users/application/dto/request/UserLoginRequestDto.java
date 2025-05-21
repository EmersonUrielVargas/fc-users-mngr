package com.foodcourt.users.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserLoginRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
