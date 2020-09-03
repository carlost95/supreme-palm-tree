package com.undec.corralon.excepciones.proveedor;

public class ProveedorCambioEstadoException extends ProveedorException {
    public ProveedorCambioEstadoException() {
        super("ProveedorCambioEstadoException: Error al dar de baja al proveedor");
    }
}
