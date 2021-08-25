package com.undec.corralon.excepciones.exception;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
