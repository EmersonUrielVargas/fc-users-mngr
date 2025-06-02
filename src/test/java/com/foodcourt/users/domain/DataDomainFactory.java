package com.foodcourt.users.domain;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.Role;
import com.foodcourt.users.domain.model.User;

public class DataDomainFactory {

    public static User createUser(){
        return User.builder()
                .email("test@pragma.com")
                .password("Assurance123")
                .idNumber("1234567")
                .name("Jane")
                .phoneNumber("+573158000111")
                .lastName("doe")
                .role(createRole())
                .build();
    }

    public static Role createEmptyRole(){
        return Role.builder().id(2L).build();
    }

    public static Role createRole(){
        return Role.builder()
                .name(UserRole.CLIENT).
                id(1L).
                build();
    }


}
