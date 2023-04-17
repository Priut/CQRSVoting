package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class UniqueConstraintViolationException extends ApplicationException {
    public UniqueConstraintViolationException(String message) {
        super(message);
    }
}
