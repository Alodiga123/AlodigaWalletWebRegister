package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MaxAmountMonthlyPerRemittentException extends Exception{
    public MaxAmountMonthlyPerRemittentException(String message) { super(message);}
    
    public MaxAmountMonthlyPerRemittentException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
     public MaxAmountMonthlyPerRemittentException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
