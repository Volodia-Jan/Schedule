package com.application.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class GroupAlreadyExistException extends RuntimeException{

    public GroupAlreadyExistException() {
    }

    public GroupAlreadyExistException(String message) {
        super(message);
    }

    public GroupAlreadyExistException(String message, Throwable cause) {
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
