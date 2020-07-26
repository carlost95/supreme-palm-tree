package com.undec.corralon.excepciones.departamento;

public class DepartamentoErrorToSaveException  extends  DepartamentoException{
    public DepartamentoErrorToSaveException() {
        super("DepartamentoErrorToSaveException: Error al crear el departamento");
    }
}
