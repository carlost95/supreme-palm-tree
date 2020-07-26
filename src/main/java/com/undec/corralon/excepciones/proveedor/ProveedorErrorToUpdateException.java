package com.undec.corralon.excepciones.proveedor;

public class ProveedorErrorToUpdateException extends ProveedorException {
    public ProveedorErrorToUpdateException (){
        super("ProveedorErrorToUpdateException: Error al almacenar el proveedor");
    }
}
