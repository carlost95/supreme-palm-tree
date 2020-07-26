package com.undec.corralon.excepciones.banco;

public class BancoNotFoundException extends BancoException {
    public BancoNotFoundException (){
        super("BancoNotFoundException: Nose encontro el Bnaco solicitado");
    }
}
