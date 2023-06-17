package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class VotingEventNotFoundException extends ApplicationException {
    public VotingEventNotFoundException(String message) {
        super(message);
    }
}