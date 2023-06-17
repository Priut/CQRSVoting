package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class DateFormatIncorrectException extends ApplicationException {
    public DateFormatIncorrectException(String message){
        super(message);
    }
}
