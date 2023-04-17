package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class VoteRegistrationException extends ApplicationException {
    public VoteRegistrationException(String message) {
        super(message);
    }
}