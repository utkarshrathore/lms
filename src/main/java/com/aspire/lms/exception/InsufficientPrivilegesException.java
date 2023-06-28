package com.aspire.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InsufficientPrivilegesException extends Exception {
    private String message;
    public InsufficientPrivilegesException(String msg) {
        super(msg);
        this.message = msg;
    }
}