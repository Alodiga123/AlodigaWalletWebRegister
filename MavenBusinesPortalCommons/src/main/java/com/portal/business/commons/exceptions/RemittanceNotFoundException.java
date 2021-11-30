package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class RemittanceNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public RemittanceNotFoundException(String message) {
        super(message);
    }

    public RemittanceNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public RemittanceNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
