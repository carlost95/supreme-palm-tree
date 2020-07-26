package com.undec.corralon.excepciones.banco;

public class BancoListNotFoundException extends BancoException {
    public BancoListNotFoundException (){
        super("BancoListNotFoundException: no se encuentra la lista de bancos solicitada");
    }
}
