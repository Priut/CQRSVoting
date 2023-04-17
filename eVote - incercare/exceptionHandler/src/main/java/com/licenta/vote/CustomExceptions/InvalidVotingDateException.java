package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class InvalidVotingDateException extends ApplicationException {
    public InvalidVotingDateException(String message) {
        super(message);
    }
}
