package com.licenta.vote.CustomExceptions;

public class NoCommandHandlerException extends RuntimeException {
    public NoCommandHandlerException(String message) {
        super(message);
    }
}