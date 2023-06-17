package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class UserDeletedException extends ApplicationException {
    public UserDeletedException(String message) {
        super(message);
    }
}
