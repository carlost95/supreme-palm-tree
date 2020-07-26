package com.undec.corralon.excepciones.banco;

public class BancoErrorToUpdateException extends BancoException {
    public BancoErrorToUpdateException(){
        super("BancoErrorToUpdateException: Error al actualizar el banco especificado");
    }
}
