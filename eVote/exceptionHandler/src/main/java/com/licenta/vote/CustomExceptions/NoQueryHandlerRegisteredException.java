package com.licenta.vote.CustomExceptions;

public class NoQueryHandlerRegisteredException extends RuntimeException {
    public NoQueryHandlerRegisteredException(String message) {
        super(message);
    }
}
