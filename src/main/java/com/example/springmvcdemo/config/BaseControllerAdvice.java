package com.example.springmvcdemo.config;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseControllerAdvice {

    // Handle failures when types from query params can't be converted to enum value
    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<String> handleFailedConversion(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
