package com.msedonald.exception;

public class UserNotFoundException extends UserException {
    private static final String MESSAGE = "해당하는 유저가 없습니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
