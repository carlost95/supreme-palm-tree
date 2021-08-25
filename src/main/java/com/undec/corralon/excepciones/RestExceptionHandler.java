package com.undec.corralon.excepciones;

import com.undec.corralon.excepciones.exception.BadRequestException;
import com.undec.corralon.excepciones.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<?>handleException(NotFoundException exc){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<?>handleException(BadRequestException exc){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
    }
}
