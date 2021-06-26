package com.undec.corralon.excepciones.banco;

public class BancoErrorToSaveException extends BancoException {
public BancoErrorToSaveException (String msg){
    super("BancoErrorToSaveException " + msg);
}
}
