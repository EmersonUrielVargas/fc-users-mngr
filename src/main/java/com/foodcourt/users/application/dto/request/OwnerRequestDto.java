package com.foodcourt.users.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
public class OwnerRequestDto {
    private String name;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
}
