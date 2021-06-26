package com.undec.corralon.excepciones.banco;

public class BancoCambioEstadoException extends BancoException {
    public BancoCambioEstadoException(String msg){
        super("BancoCambioEstadoException: " + msg);
    }
}
