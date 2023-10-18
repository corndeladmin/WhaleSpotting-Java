package com.whalespottingjava.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springdoc.api.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({ ConstraintViolationException.class })
    @ResponseBody
    public ResponseEntity<ErrorMessage> constraintViolationHandler(ConstraintViolationException ex, WebRequest request) {
        String violations = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(
                new ErrorMessage(violations),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    @ResponseBody
    public ResponseEntity<ErrorMessage> dataIntegrityViolationHandler(DataIntegrityViolationException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorMessage("Bad Request"),
                HttpStatus.BAD_REQUEST
        );
    }
}
