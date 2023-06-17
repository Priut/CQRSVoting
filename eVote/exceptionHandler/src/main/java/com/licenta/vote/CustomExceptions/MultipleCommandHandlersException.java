package com.licenta.vote.CustomExceptions;

public class MultipleCommandHandlersException extends RuntimeException {
    public MultipleCommandHandlersException(String message) {
        super(message);
    }
}