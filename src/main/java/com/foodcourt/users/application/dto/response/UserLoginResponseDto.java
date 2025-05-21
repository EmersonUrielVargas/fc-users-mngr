package com.foodcourt.users.application.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserLoginResponseDto {
    @NotNull
    String authToken;
}
