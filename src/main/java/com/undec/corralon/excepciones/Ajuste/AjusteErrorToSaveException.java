package com.undec.corralon.excepciones.Ajuste;

public class AjusteErrorToSaveException extends AjusteException {
    public AjusteErrorToSaveException(String mensage){
        super("AjusteErrorToSaveException: Se genero un error al guardar ajuste");
    }
}
