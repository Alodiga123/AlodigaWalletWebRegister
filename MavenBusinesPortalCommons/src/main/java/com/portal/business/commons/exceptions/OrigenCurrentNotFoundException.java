package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class OrigenCurrentNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public OrigenCurrentNotFoundException(String message) {
        super(message);
    }

    public OrigenCurrentNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public OrigenCurrentNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
