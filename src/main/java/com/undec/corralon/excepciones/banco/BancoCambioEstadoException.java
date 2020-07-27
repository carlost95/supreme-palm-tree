package com.undec.corralon.excepciones.banco;

public class BancoCambioEstadoException extends BancoException {
    public BancoCambioEstadoException(){
        super("BancoCambioEstadoException: Error al dar de baja al banco");
    }
}
