package com.undec.corralon.excepciones.departamento;

public class DepartamentoListNotFoundException extends DepartamentoException {
    public DepartamentoListNotFoundException() {
        super("DepartamentoListNotFoundException: Error en al recuperar datos");
    }
}
