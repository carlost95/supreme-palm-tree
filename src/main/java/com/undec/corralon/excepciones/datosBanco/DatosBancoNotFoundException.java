package com.undec.corralon.excepciones.datosBanco;

public class DatosBancoNotFoundException extends DatosBancoException {
    public DatosBancoNotFoundException(String msg){
        super("DatosBancoNotFoundException "+msg);
    }
}
