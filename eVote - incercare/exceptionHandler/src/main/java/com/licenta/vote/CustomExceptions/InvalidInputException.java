package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class InvalidInputException extends ApplicationException {
    public InvalidInputException(String message) {
        super(message);
    }
}