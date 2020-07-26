package com.undec.corralon.excepciones.proveedor;

public class ProveedorErrorToSaveException extends ProveedorException{
    public ProveedorErrorToSaveException (){
        super("ProveedorErrorToSaveException: No se pudo guardar el proveedor enviado");
    }
}
