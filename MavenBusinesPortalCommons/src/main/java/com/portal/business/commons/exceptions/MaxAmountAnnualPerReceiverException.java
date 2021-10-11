package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MaxAmountAnnualPerReceiverException extends Exception{
    public MaxAmountAnnualPerReceiverException(String message) { super(message);}
    
    public MaxAmountAnnualPerReceiverException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
     public MaxAmountAnnualPerReceiverException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
