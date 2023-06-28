package com.aspire.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class IllegalStateException extends Exception {
    private String message;
    public IllegalStateException(String msg) {
        super(msg);
        this.message = msg;
    }
}