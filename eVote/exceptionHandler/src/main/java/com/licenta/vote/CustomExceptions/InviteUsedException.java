package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;


public class InviteUsedException extends ApplicationException {
    public InviteUsedException(String message){
        super(message);
    }
}