package com.undec.corralon.excepciones.articulo;

public class ArticuloErrorToSaveException extends ArticuloException {
    public ArticuloErrorToSaveException() {
        super("ArticuloErrorToSaveException: Se produjo error al guardar");
    }
}
