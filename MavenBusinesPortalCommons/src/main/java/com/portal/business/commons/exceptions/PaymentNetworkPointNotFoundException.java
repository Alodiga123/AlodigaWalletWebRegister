package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class PaymentNetworkPointNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public PaymentNetworkPointNotFoundException(String message) {
        super(message);
    }

    public PaymentNetworkPointNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public PaymentNetworkPointNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
