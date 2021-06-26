package com.undec.corralon.excepciones.proveedor;

public class ProveedorHabilitadosNotFoundException extends ProveedorException {
    public ProveedorHabilitadosNotFoundException(String msg){
        super("ProveedorHabilitadosNotFoundException: "+ msg);
    }
}
