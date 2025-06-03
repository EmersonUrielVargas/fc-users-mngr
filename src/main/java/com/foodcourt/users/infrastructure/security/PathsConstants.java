package com.foodcourt.users.infrastructure.security;

import com.foodcourt.users.domain.constants.Constants;

public class PathsConstants {
    private PathsConstants() {
        throw new IllegalStateException(Constants.INSTANCE_UTILITY_CLASS);
    }


    protected static final String[] PATHS_ALL_ALLOW = {
            "/public/**",
            "/swagger-ui**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/auth/**",
            "/role/*",
            "/client"
    };

    protected static final String[] PATHS_EMPLOYEE = {
            "/contact/*"
    };

    protected static final String[] PATHS_OWNER = {
            "/employee"
    };

    protected static final String[] PATHS_ADMIN = {
            "/owner"
    };
}
