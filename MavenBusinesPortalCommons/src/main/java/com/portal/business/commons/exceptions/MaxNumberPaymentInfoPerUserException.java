package com.portal.business.commons.exceptions;

@SuppressWarnings("serial")
public class MaxNumberPaymentInfoPerUserException extends Exception{
    public MaxNumberPaymentInfoPerUserException(String message) { super(message);}
    
    public MaxNumberPaymentInfoPerUserException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
