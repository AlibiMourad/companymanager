package com.sparkers.companymanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(WhateverException.class)
    public ResponseEntity<Object> anyFoundException(WhateverException ex) {
        return new ResponseEntity<>(getBody(ex.getStatus(), ex.getMessage()), ex.getStatus());
    }

    @ExceptionHandler(PartnerNotFoundException.class)
    public ResponseEntity<Object> handlePartnerNotFoundException(PartnerNotFoundException ex) {
        return new ResponseEntity<>(getBody(ex.getStatus(), ex.getMessage()), ex.getStatus());
    }

    @ExceptionHandler(ValueValidateException.class)
    ResponseEntity<Object> handleConstraintViolationException(ValueValidateException ex) {
        return new ResponseEntity<>(getBody(ex.getStatus(), ex.getMessage()), ex.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleExpirationTimeException(HttpMessageNotReadableException ex) {
        return handleConstraintViolationException(new ValueValidateException());
    }

    private Map<String, Object> getBody(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", status.value());
        body.put("message", message);
        return body;
    }
}
