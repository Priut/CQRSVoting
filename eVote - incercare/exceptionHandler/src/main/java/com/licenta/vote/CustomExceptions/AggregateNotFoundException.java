package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class AggregateNotFoundException extends ApplicationException {
    public AggregateNotFoundException(String message){
        super(message);
    }
}
