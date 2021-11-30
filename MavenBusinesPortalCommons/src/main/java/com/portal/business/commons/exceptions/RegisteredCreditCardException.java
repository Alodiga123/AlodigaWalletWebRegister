package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class RegisteredCreditCardException extends Exception {

    private static final long serialVersionUID = 1L;

    public RegisteredCreditCardException(String message) {
        super(message);
    }

    public RegisteredCreditCardException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);

    }

    public RegisteredCreditCardException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
