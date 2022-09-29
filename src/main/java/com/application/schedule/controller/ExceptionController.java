package com.application.schedule.controller;

import com.application.schedule.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse studentNotFoundExceptionHandler(StudentNotFoundException exception) {
        return runtimeExceptionHandler(exception);
    }

    @ExceptionHandler(EnumConstantNotPresentException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ResponseBody
    public ErrorResponse emailAlreadyExistExceptionHandler(EmailAlreadyExistException exception) {
        return runtimeExceptionHandler(exception);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse groupNotFroundExceptionHandler(GroupNotFoundException exception) {
        return runtimeExceptionHandler(exception);
    }

    @ExceptionHandler(GroupAlreadyExistException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ResponseBody
    public ErrorResponse groupAlreadyExistExceptionHandler(GroupAlreadyExistException exception) {
        return runtimeExceptionHandler(exception);
    }

    private ErrorResponse runtimeExceptionHandler(RuntimeException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return errorResponse;
    }
}
