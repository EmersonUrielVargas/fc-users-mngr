package com.foodcourt.users.infrastructure.security.dto;

import com.foodcourt.users.domain.enums.UserRole;
import com.foodcourt.users.domain.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsDto implements UserDetails {

    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Long idUser;
    @NotNull
    private UserRole role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
