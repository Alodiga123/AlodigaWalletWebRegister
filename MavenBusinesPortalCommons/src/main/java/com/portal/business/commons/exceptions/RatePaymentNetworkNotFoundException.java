package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class RatePaymentNetworkNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public RatePaymentNetworkNotFoundException(String message) {
        super(message);
    }

    public RatePaymentNetworkNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public RatePaymentNetworkNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
