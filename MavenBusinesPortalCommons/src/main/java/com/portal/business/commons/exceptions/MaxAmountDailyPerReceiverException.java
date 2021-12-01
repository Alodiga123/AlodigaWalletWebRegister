package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MaxAmountDailyPerReceiverException extends Exception{
    public MaxAmountDailyPerReceiverException(String message) { super(message);}
    
    public MaxAmountDailyPerReceiverException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
     public MaxAmountDailyPerReceiverException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
