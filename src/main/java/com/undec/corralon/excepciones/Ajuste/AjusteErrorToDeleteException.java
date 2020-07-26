package com.undec.corralon.excepciones.Ajuste;

public class AjusteErrorToDeleteException extends AjuesteException {
    AjusteErrorToDeleteException(String mensage){
        super("AjusteErrorToDeleteException: Error al aliminar ajuste");
    }
}
