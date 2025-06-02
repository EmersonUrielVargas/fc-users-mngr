package com.foodcourt.users.domain.exception;

public class MissingParamRequiredException extends DomainException {
    public MissingParamRequiredException(String message) {
        super(message);
    }
}
