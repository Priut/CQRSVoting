package com.licenta.vote.CustomExceptions;

public class MultipleQueryHandlersFoundException extends RuntimeException {
    public MultipleQueryHandlersFoundException(String message) {
        super(message);
    }
}