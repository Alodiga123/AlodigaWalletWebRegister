package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MaxAmountMonthlyPerReceiverException extends Exception{
    public MaxAmountMonthlyPerReceiverException(String message) { super(message);}
    
    public MaxAmountMonthlyPerReceiverException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
     public MaxAmountMonthlyPerReceiverException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
