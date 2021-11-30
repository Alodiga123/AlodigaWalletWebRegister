package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class DocumentNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public DocumentNotFoundException(String message) {
        super(message);
    }

    public DocumentNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public DocumentNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
