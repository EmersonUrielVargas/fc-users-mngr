package com.foodcourt.users.domain.exception;

import com.foodcourt.users.domain.constants.Constants;

public class RoleNotFoundException extends DomainException {
    public RoleNotFoundException(String message) {
        super(message);
    }
    public RoleNotFoundException() {
        super(Constants.ROLE_NO_FOUND);
    }

}
