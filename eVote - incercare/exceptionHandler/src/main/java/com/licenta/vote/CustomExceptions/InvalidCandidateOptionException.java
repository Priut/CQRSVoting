package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class InvalidCandidateOptionException extends ApplicationException {
    public InvalidCandidateOptionException(String message){
        super(message);
    }
}