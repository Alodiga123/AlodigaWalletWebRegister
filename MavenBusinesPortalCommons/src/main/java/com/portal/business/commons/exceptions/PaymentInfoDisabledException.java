package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class PaymentInfoDisabledException extends Exception {

    private static final long serialVersionUID = 1L;

    public PaymentInfoDisabledException(String message) {
        super(message);
    }

    public PaymentInfoDisabledException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public PaymentInfoDisabledException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
