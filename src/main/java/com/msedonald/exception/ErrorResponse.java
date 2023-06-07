package com.msedonald.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final String code;
    private final String errorMessage;
    private final LocalDateTime timestamp;

    @Builder
    public ErrorResponse(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }
}
