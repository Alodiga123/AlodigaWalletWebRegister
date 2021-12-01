package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class RemittenceNotAvailableException extends Exception{
    public RemittenceNotAvailableException(String message) { super(message);}
    
    public RemittenceNotAvailableException(String message,
        StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
    
    public RemittenceNotAvailableException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }
}
