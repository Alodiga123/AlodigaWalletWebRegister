package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class InvalidPaymentInfoException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidPaymentInfoException(String message) {
        super(message);
    }

    public InvalidPaymentInfoException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public InvalidPaymentInfoException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
