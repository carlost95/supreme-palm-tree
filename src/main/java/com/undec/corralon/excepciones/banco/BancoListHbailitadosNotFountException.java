package com.undec.corralon.excepciones.banco;

public class BancoListHbailitadosNotFountException extends BancoException {
    public BancoListHbailitadosNotFountException(String msg){
        super("BancoListHbailitadosNotFountException "+msg);
    }
}
