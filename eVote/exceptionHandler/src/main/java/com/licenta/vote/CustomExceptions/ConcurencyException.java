package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class ConcurencyException extends ApplicationException {
    public ConcurencyException(String message) {
        super(message);
    }
}
