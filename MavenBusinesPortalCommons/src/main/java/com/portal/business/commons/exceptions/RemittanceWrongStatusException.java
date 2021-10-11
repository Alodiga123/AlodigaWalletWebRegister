package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class RemittanceWrongStatusException extends Exception { 

	private static final long serialVersionUID = 1L;

    public RemittanceWrongStatusException(String message) {
        super(message);
    }

    public RemittanceWrongStatusException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public RemittanceWrongStatusException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
	
}
