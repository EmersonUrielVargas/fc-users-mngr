package com.foodcourt.users.application.dto.request;

import com.foodcourt.users.domain.model.Role;

import java.time.LocalDate;

public class OwnerRequestDto {
    private String name;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
}
