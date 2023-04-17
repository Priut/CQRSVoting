package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;


public class VotingEventDeletedException extends ApplicationException {
    public VotingEventDeletedException(String message) {
        super(message);
    }
}