package com.undec.corralon.excepciones.marca;

public class MarcaNotFoundException extends Exception {
    public MarcaNotFoundException() {
        super("MarcaNotFoundException: No se encontro ninguna marca en la bd");
    }
}
