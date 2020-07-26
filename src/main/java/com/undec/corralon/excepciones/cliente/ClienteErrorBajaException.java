package com.undec.corralon.excepciones.cliente;

public class ClienteErrorBajaException extends ClienteException{
    public ClienteErrorBajaException() {
        super("ClienteErrorBajaException: Error al dar de baja");
    }

}
