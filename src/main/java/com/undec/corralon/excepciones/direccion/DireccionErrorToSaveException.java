package com.undec.corralon.excepciones;

public class DireccionErrorToSaveException extends Exception {
    public DireccionErrorToSaveException(){
        super("DireccionErrorToSaveException: Se genero error al guardar");
    }
}
