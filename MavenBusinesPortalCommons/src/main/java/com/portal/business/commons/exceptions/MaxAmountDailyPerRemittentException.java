package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MaxAmountDailyPerRemittentException extends Exception{
    public MaxAmountDailyPerRemittentException(String message) { super(message);}
    
    public MaxAmountDailyPerRemittentException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
     public MaxAmountDailyPerRemittentException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
