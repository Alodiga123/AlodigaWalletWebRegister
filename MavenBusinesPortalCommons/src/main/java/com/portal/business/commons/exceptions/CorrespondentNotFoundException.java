package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class CorrespondentNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CorrespondentNotFoundException(String message) {
        super(message);
    }

    public CorrespondentNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public CorrespondentNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
