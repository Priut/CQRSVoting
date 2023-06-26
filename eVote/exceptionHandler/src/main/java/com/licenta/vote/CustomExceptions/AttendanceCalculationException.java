package com.licenta.vote.CustomExceptions;

import com.licenta.vote.ApplicationException;
public class AttendanceCalculationException extends ApplicationException {
    public AttendanceCalculationException(String message){
        super(message);
    }
}