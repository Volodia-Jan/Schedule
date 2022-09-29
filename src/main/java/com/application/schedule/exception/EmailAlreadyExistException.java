package com.application.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class EmailAlreadyExistException extends RuntimeException{

    public EmailAlreadyExistException() {
    }

    public EmailAlreadyExistException(String message) {
        super(message);
    }

    public EmailAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}
