package com.foodcourt.users.infrastructure.security.adapter;

import com.foodcourt.users.domain.spi.IPasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class PasswordEncoderAdapter implements IPasswordEncoderPort {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String encoder(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String inputPassword, String encodedPassword) {
        return passwordEncoder.matches(inputPassword, encodedPassword);
    }
}
