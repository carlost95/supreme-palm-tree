package com.undec.corralon.excepciones.cliente;

public class ClienteNotFounsException extends ClienteException {
    public ClienteNotFounsException(){
        super("ClienteNotFoundException, No se encontro el cliente");
    }
}
