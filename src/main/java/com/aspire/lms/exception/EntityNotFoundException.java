package com.aspire.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {

    private String message;
    public EntityNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
