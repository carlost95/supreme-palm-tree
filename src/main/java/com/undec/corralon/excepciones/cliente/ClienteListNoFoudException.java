package com.undec.corralon.excepciones.cliente;

public class ClienteListNoFoudException extends ClienteException {
            public ClienteListNoFoudException() {
            super("ClienteListNotFoundException: Error en al recuperar datos");
        }
}
