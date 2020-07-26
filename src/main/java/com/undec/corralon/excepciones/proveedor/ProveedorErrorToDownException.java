package com.undec.corralon.excepciones.proveedor;

public class ProveedorErrorToDownException extends ProveedorException {
    public ProveedorErrorToDownException (){
       super("ProveedorErrorToDownException: Error al dar de baja el proveedor");
    }
}
