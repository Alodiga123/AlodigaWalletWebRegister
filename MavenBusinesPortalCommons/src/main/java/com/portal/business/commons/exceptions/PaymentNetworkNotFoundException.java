package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class PaymentNetworkNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public PaymentNetworkNotFoundException(String message) {
        super(message);
    }

    public PaymentNetworkNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public PaymentNetworkNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
