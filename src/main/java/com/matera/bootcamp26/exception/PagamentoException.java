package com.matera.bootcamp26.exception;

import org.springframework.http.HttpStatus;

public class PagamentoException extends RuntimeException {

    private final HttpStatus status;

    public PagamentoException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
