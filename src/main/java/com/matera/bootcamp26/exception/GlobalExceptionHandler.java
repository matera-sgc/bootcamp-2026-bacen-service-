package com.matera.bootcamp26.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContaException.class)
    public ResponseEntity<ProblemDetail> handleContaException(ContaException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        problemDetail.setTitle("Conta inválida");
        return ResponseEntity.status(ex.getStatus()).body(problemDetail);
    }
}
