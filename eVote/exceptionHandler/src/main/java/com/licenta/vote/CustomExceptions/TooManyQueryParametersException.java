package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class TooManyQueryParametersException extends ApplicationException {
    public TooManyQueryParametersException(String message) {
        super(message);
    }
}
