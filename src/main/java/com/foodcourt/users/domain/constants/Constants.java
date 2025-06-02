package com.foodcourt.users.domain.constants;

public class Constants {// Compliant

    private Constants() {
        throw new IllegalStateException(INSTANCE_UTILITY_CLASS);
    }

    public static final String INSTANCE_UTILITY_CLASS = "Utility class can not create a instance";

    /*Descripcion Errores*/
    public static final String INVALID_EMAIL = "El email no es valido ";
    public static final String INVALID_AGE = "La edad no es valida";
    public static final String INVALID_PHONE_NUMBER = "El numero de telefono no es valido ";
    public static final String INVALID_ID_NUMBER = "El documento de identidad no es valido ";
    public static final String ROLE_NO_FOUND = "El rol no fue encontrado ";
    public static final String USER_NO_FOUND = "El usuario no fue encontrado ";
    public static final String INVALID_CREDENTIALS = "Las credenciales son invalidas, verifique informacion";
    public static final String ERROR_GENERATE_TOKEN = "Ocurrio un error al generar el token";
    public static final String ROLE_USER_NO_MATCHED = "El id rol no corresponde al del usuario a ingresar ";

    public static final String PARAM_REQUIRED_NOT_FOUND = "Los parametros requeridos no pueden ser nulos ";

    /*Patrones propiedades*/
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final Integer MIN_AGE = 18;
    public static final String PHONE_NUMBER_PATTERN = "^\\+?[0-9]{1,12}$";
    public static final String ID_NUMBER_PATTERN = "^[0-9]+$";

}
