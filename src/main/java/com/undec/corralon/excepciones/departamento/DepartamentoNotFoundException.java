package com.undec.corralon.excepciones.departamento;

public class DepartamentoNotFoundException  extends  DepartamentoException{
    public DepartamentoNotFoundException() {
        super("DepartamentoNotFoundException: No se encontro el departamento");
    }
}
