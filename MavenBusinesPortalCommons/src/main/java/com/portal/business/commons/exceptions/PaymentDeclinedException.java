package com.portal.business.commons.exceptions;

@SuppressWarnings("serial")
public class PaymentDeclinedException extends Exception{
    public PaymentDeclinedException(String message) { super(message);}
    
    public PaymentDeclinedException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
