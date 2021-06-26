package com.undec.corralon.excepciones.proveedor;

public class ProveedorNotFoundException extends ProveedorException {
    public ProveedorNotFoundException(String msg){
        super ("ProveedorNotFoundException:" + msg);
    }
}
