package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MaxAmountDailyPerStoreException extends Exception{
    public MaxAmountDailyPerStoreException(String message) { super(message);}
    
    public MaxAmountDailyPerStoreException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
     public MaxAmountDailyPerStoreException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
