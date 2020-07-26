package com.undec.corralon.excepciones.Ajuste;

public class AjusteErrorToUpdateException extends AjuesteException {
    AjusteErrorToUpdateException (String mensage){
        super("AjusteErrorToUpdateException: error al actualizar ajuste");
    }
}
