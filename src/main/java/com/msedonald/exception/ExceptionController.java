package com.msedonald.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ErrorResponse userExceptionHandler(UserException ex) {
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .errorMessage(ex.getMessage())
                .build();
    }

}
