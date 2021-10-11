package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class MaxAmountPerRemettenceException extends Exception{
    public MaxAmountPerRemettenceException(String message) { super(message);}
    
    public MaxAmountPerRemettenceException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
    public MaxAmountPerRemettenceException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
