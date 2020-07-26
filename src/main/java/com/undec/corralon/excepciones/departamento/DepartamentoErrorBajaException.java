package com.undec.corralon.excepciones.departamento;

public class DepartamentoErrorBajaException  extends DepartamentoException{
    public DepartamentoErrorBajaException() {
        super("DepartamentoErrorBajaException: Error al dar de baja");
    }
}
