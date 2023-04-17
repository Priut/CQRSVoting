package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class VotingEventExpiredException extends ApplicationException {
    public VotingEventExpiredException(String message) {
        super(message);
    }
}