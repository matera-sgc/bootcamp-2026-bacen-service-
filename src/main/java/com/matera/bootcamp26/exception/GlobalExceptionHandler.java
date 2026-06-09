package com.matera.bootcamp26.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContaException.class)
    public ResponseEntity<ProblemDetail> handleContaException(ContaException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        problemDetail.setTitle("Conta inválida");
        return ResponseEntity.status(ex.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(PagamentoException.class)
    public ResponseEntity<ProblemDetail> handlePagamentoException(PagamentoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        problemDetail.setTitle("Pagamento inválida");
        problemDetail.setType(URI.create("https://bootcamp.com/docs/pagamento-invalido"));
        return ResponseEntity.status(ex.getStatus()).body(problemDetail);
    }
}
