package com.foodcourt.users.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

    private Long id;
    private String name;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Role role;

}
