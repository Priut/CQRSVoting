package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}