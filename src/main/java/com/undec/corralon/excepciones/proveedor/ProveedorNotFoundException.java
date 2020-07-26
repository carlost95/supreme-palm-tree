package com.undec.corralon.excepciones.proveedor;

public class ProveedorNotFoundException extends ProveedorException {
    public ProveedorNotFoundException(){
        super ("ProveedorNotFoundException: No existen proveesores cargados");
    }
}
