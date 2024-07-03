package com.Bau.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PokemonNotFounexception.class)
    public ResponseEntity<ErrorObject> handlePokemonNotFounexception(PokemonNotFounexception ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setCode(HttpStatus.NOT_FOUND.value());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorObject> handleReviewNotFoundException(ReviewNotFoundException  ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setCode(HttpStatus.NOT_FOUND.value());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }
}
