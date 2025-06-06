package com.foodcourt.users.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OwnerRequestDto extends CreateBasicUserRequestDto{
    @NotNull
    private LocalDate birthDate;
}
