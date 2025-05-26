package com.foodcourt.users.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientRequestDto extends CreateBasicUserRequestDto{
    @NotNull
    private Long idRole;

}
