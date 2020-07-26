package com.undec.corralon.excepciones.formaDePago;

public class FormaDePagoErrorToSaveException extends Exception {

    public FormaDePagoErrorToSaveException() {
        super("FormaDePagoErrorToSaveException: Error al guardar forma de pago");
    }
}
