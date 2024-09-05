package com.example.price.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(PriceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Message\": \"" + e.getMessage() + "\"}");
    }
}