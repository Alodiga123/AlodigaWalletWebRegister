package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class RemittentNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public RemittentNotFoundException(String message) {
        super(message);
    }

    public RemittentNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public RemittentNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
