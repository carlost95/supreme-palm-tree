package com.undec.corralon.excepciones.articulo;

public class ArticuloErrorToUpdateException extends ArticuloException {
    public ArticuloErrorToUpdateException() {
        super("ArticuloErrorToUpdateException: Error al actualizar el producto");
    }
}
