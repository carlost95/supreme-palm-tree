package com.undec.corralon.excepciones.exception;

public class NotFounException extends RuntimeException {
    public NotFounException() {
    }

    public NotFounException(String message) {
        super(message);
    }
}
