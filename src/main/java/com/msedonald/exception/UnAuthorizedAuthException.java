package com.msedonald.exception;

public class UnAuthorizedAuthException extends UserAuthException {
    private static final String MESSAGE = "인증되지 않은 접근입니다.";

    public UnAuthorizedAuthException() {
        super(MESSAGE);
    }
}
