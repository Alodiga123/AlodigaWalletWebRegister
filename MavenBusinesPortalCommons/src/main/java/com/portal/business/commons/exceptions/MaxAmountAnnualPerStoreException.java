package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MaxAmountAnnualPerStoreException extends Exception{
    public MaxAmountAnnualPerStoreException(String message) { super(message);}
    
    public MaxAmountAnnualPerStoreException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
     public MaxAmountAnnualPerStoreException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
