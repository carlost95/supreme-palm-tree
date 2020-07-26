package com.undec.corralon.excepciones.cliente;

public class ClienteErrorToSaveException extends ClienteException{
    public ClienteErrorToSaveException (){
        super("ClienteErrorToSaveException: ERROR al guardar cliente");
    }
}
