package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class DestinyCurrentNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public DestinyCurrentNotFoundException(String message) {
        super(message);
    }

    public DestinyCurrentNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public DestinyCurrentNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
