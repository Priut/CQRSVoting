package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class FutureBirthDateException extends ApplicationException {
    public FutureBirthDateException(String message){
        super(message);
    }
}