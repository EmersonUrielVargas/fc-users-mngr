package com.foodcourt.users.infrastructure.shared;

public class Constants {

    public static final String STATUS_CODE_CREATED= "201";
    public static final String STATUS_CODE_CONFLICT= "409";
    public static final String STATUS_CODE_OK= "200";
    public static final String STATUS_CODE_NOT_FOUND= "404";

    public static final String MEDIA_TYPE_JSON= "application/json";

    public static final String SUMMARY_LOGIN_USER = "User Login with credentials";
    public static final String SUMMARY_RESPONSE_OK_LOGIN_USER = "Login successful return token to use protected services";
    public static final String SUMMARY_RESPONSE_CONFLICT_LOGIN_USER = "User not found or invalid credentials";

    public static final String SUMMARY_SAVE_OWNER = "Create a new Owner user to administrate restaurants and employees";
    public static final String SUMMARY_RESPONSE_CREATED_SAVE_OWNER = "Owner created successful";
    public static final String SUMMARY_RESPONSE_CONFLICT_SAVE_OWNER = "Owner already exists o information inconsistent";

    public static final String SUMMARY_GET_ROLE_BY_USER_ID = "Get User Role by user id";
    public static final String SUMMARY_RESPONSE_OK_GET_ROLE = "User Found and return role name of user";
    public static final String SUMMARY_RESPONSE_CONFLICT_GET_ROLE = "User not found";

    public static final String SUMMARY_SAVE_EMPLOYEE = "Create a new employee user to take orders";
    public static final String SUMMARY_RESPONSE_CREATED_SAVE_EMPLOYEE = "employee created successful";
    public static final String SUMMARY_RESPONSE_CONFLICT_SAVE_EMPLOYEE = "employee information to created is invalid";

    public static final String SUMMARY_SAVE_CLIENT = "Create a new client user to see restaurants and make orders";
    public static final String SUMMARY_RESPONSE_CREATED_SAVE_CLIENT = "Client created successful";
    public static final String SUMMARY_RESPONSE_CONFLICT_SAVE_CLIENT = "Client information to created is invalid";

    /* Security */
    public static final String ROL_KEY_TOKEN = "role";
    public static final String USER_ID_KEY_TOKEN = "userID";
    public static final String AUTH_HEADER_NAME = "Authorization";
    public static final String AUTH_TOKEN_PREFIX = "Bearer ";
    public static final String DEFAULT_PARAM_EXCEPTION_RESPONSE = "message";

}
