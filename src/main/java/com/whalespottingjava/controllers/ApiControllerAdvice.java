package com.whalespottingjava.controllers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String dataIntegrityViolationHandler(Exception ex) {
        return ex.getLocalizedMessage(); // TODO: ws-102: This reveals implementation details on the database, error messages should be overridden
    }
}
