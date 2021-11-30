package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class InvalidCreditCardTypeException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidCreditCardTypeException(String message) {
        super(message);
    }

    public InvalidCreditCardTypeException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public InvalidCreditCardTypeException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
