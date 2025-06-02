package com.foodcourt.users.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_DATA_FOUND("No se encontró datos para la petición"),
    GENERAL_ERROR("Se ha producido un error inesperado"),
    USER_NOT_FOUND_ERROR("Usuario no encontrado, verifique sus credenciales"),
    INFORMATION_ERROR("Se ha producido un error con la informacion suministrada, revisa los datos e intentalo nuevamente");


    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}