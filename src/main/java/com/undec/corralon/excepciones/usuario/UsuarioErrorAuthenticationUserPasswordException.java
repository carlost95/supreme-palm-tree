package com.undec.corralon.excepciones.usuario;

public class UsuarioErrorAuthenticationUserPasswordException extends UsuarioException{
    public UsuarioErrorAuthenticationUserPasswordException(){
        super ("UsuarioErrorAuthenticationUserPasswordException: la contraseña no es correcta");
    }
}
