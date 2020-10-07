package com.undec.corralon.excepciones.Ajuste;

public class AjusteErrorToUpdateException extends AjusteException {
    public AjusteErrorToUpdateException(String mensage){
        super("AjusteErrorToUpdateException: error al actualizar ajuste");
    }
}
