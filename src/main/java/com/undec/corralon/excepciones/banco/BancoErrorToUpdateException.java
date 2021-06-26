package com.undec.corralon.excepciones.banco;

public class BancoErrorToUpdateException extends BancoException {
    public BancoErrorToUpdateException(String msg){
        super("BancoErrorToUpdateException "+msg);
    }
}
