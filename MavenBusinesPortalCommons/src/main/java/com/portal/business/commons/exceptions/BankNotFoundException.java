package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class BankNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public BankNotFoundException(String message) {
        super(message);
    }

    public BankNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public BankNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
