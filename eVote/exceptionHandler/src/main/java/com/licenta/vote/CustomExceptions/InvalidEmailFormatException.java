package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class InvalidEmailFormatException extends ApplicationException {
    public InvalidEmailFormatException(String message){
        super(message);
    }
}