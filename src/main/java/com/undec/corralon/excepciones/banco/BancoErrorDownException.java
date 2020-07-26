package com.undec.corralon.excepciones.banco;

public class BancoErrorDownException extends BancoException {
    public BancoErrorDownException(){
        super("BancoErrorDownException: Error al dar de baja al banco");
    }
}
