package com.undec.corralon.excepciones.banco;

public class BancoErrorToSaveException extends BancoException {
public BancoErrorToSaveException (){
    super("BancoErrorToSaveException: Error al guardar el banco especificado");
}
}
