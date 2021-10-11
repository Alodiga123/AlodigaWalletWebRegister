package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class InvalidCreditCardNumberException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidCreditCardNumberException(String message) {
        super(message);
    }

    public InvalidCreditCardNumberException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public InvalidCreditCardNumberException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
