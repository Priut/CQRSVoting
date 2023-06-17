package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class InviteNotFoundException extends ApplicationException {
    public InviteNotFoundException(String message) {
        super(message);
    }
}
