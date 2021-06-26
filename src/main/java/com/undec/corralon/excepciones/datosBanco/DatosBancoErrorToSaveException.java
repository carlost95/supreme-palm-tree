package com.undec.corralon.excepciones.datosBanco;

public class DatosBancoErrorToSaveException extends  DatosBancoException {
    public DatosBancoErrorToSaveException(String msg){
        super("DatosBancoErrorToSaveException "+ msg);
    }
}
