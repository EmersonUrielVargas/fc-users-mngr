package com.foodcourt.users.domain.model;

import com.foodcourt.users.domain.enums.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Role {

    private Long id;
    private UserRole name;
    private String description;
}
