package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;
//TODO de verificat dupa ce faci metoda de modificare a datei pt votingEvent pt demo
public class InvalidVotingDateException extends ApplicationException {
    public InvalidVotingDateException(String message) {
        super(message);
    }
}
