package com.msedonald.exception;

public class UserNotFoundAuthException extends RuntimeException {
    private static final String MESSAGE = "해당하는 유저가 없습니다.";

    public UserNotFoundAuthException() {
        super(MESSAGE);
    }
}
