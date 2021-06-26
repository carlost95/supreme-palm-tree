package com.undec.corralon.excepciones.proveedor;

public class ProveedorErrorToSaveException extends ProveedorException{
    public ProveedorErrorToSaveException (String msj){
        super("ProveedorErrorToSaveException "+msj);
    }
}
