package com.aspire.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    private String message;
    public ResourceNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
