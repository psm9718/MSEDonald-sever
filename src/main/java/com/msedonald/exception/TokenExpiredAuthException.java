package com.msedonald.exception;

public class TokenExpiredAuthException extends UserAuthException {
    private static final String MESSAGE = "토큰이 만료되었습니다.";

    public TokenExpiredAuthException() {
        super(MESSAGE);
    }
}
