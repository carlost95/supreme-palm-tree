package com.undec.corralon.excepciones.banco;

public class BancoListNotFoundException extends BancoException {
    public BancoListNotFoundException (String msg){
        super("BancoListNotFoundException" + msg);
    }
}
