package com.msedonald.exception;

public class MessageSendingException extends RuntimeException {

    private static final String MESSAGE = "Error sending message";

    public MessageSendingException() {
        super(MESSAGE);
    }
}

