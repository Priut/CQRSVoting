package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class InvalidPhoneNumberException extends ApplicationException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}