package com.undec.corralon.excepciones.articulo;

public class ArticuloErrorToDeleteException extends ArticuloException {
    public ArticuloErrorToDeleteException() {
        super("ArticuloErrorToDeleteException: Se produjo error al dar de baja un articulo");
    }
}
