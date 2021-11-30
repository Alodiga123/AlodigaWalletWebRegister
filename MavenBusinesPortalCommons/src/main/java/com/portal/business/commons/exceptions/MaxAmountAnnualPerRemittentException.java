package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MaxAmountAnnualPerRemittentException extends Exception{
    public MaxAmountAnnualPerRemittentException(String message) { super(message);}
    
    public MaxAmountAnnualPerRemittentException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
     public MaxAmountAnnualPerRemittentException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
