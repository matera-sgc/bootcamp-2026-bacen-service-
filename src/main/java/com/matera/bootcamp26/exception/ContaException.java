package com.matera.bootcamp26.exception;

import org.springframework.http.HttpStatus;

public class ContaException extends RuntimeException {

    private final HttpStatus status;

    public ContaException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
