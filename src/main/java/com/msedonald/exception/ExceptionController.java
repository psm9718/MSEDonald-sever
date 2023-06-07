package com.msedonald.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundAuthException.class)
    public ErrorResponse userExceptionHandler(UserNotFoundAuthException ex) {
        return ErrorResponse.builder()
                .code(NOT_FOUND.getReasonPhrase())
                .errorMessage(ex.getMessage())
                .build();
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(UserAuthException.class)
    public ErrorResponse unauthorizedException(UserAuthException ex) {
        return ErrorResponse.builder()
                .code(UNAUTHORIZED.getReasonPhrase())
                .errorMessage(ex.getMessage())
                .build();
    }
}
