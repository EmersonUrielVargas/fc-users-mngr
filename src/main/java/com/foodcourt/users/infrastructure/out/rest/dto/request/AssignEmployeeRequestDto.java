package com.foodcourt.users.infrastructure.out.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AssignEmployeeRequestDto {
    @NotNull
    private Long idEmployee;
}
