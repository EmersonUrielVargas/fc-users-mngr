package com.foodcourt.users.domain.spi;

public interface IPasswordEncoderPort {
    String encoder(String password);
    boolean matches(String inputPassword, String encodedPassword);
}
