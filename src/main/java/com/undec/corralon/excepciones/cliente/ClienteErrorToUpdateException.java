package com.undec.corralon.excepciones.cliente;

public class ClienteErrorToUpdateException extends ClienteException {
    public ClienteErrorToUpdateException (){
        super("ClienteErrorToUpdateException: ERROR al Actuzalizar Cliente");
    }
}
