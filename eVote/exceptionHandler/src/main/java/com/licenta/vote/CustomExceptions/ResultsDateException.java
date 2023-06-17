package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class ResultsDateException extends ApplicationException {
    public ResultsDateException(String message) {
        super(message);
    }
}