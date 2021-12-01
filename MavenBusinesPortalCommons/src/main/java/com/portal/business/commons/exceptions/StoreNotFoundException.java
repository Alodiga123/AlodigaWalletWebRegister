package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class StoreNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public StoreNotFoundException(String message) {
        super(message);
    }

    public StoreNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public StoreNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
