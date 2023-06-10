package com.msedonald.exception;

public class UnAuthorizedException extends UserAuthException {
    private static final String MESSAGE = "인증되지 않은 접근입니다.";

    public UnAuthorizedException() {
        super(MESSAGE);
    }
}
