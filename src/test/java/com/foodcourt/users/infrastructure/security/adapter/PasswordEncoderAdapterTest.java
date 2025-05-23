package com.foodcourt.users.infrastructure.security.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordEncoderAdapterTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private PasswordEncoderAdapter passwordEncoderAdapter;

    @Test
    void shouldEncoderPassword() {
        when(bCryptPasswordEncoder.encode("1234")).thenReturn("encoded1234");

        String result = passwordEncoderAdapter.encoder("1234");

        assertEquals("encoded1234", result);
        verify(bCryptPasswordEncoder).encode("1234");
    }

    @Test
    void shouldMatchPassword() {
        when(bCryptPasswordEncoder.matches("1234", "encoded")).thenReturn(true);
        boolean result = passwordEncoderAdapter.matches("1234", "encoded");

        assertTrue(result);
        verify(bCryptPasswordEncoder).matches("1234", "encoded");
    }
}