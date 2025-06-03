package com.foodcourt.users.application.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserContactInfoResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
}
