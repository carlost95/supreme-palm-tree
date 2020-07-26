package com.undec.corralon.excepciones.rubro;

public class RubroErrorToDeleteException extends RubroException {
    public RubroErrorToDeleteException() {
        super("RubroErrorToDeleteException: Se ha producido un error al eliminar el rubro");
    }
}
