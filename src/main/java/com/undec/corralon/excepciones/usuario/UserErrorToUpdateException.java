package com.undec.corralon.excepciones.usuario;

public class UserErrorToUpdateException extends UsuarioException {
    public UserErrorToUpdateException(String msg) {
        super(msg + "no se pudo actualizar el usuario");
    }
}
