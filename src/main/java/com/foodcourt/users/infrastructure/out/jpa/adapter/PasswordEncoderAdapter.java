package com.foodcourt.users.infrastructure.out.jpa.adapter;

import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderAdapter implements IPasswordEncoderPort {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encoder(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String inputPassword, String encodedPassword) {
        return passwordEncoder.matches(inputPassword, encodedPassword);
    }
}
