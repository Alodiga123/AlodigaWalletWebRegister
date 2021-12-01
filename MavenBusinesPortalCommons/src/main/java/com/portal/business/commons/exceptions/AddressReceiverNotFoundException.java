package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class AddressReceiverNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public AddressReceiverNotFoundException(String message) {
        super(message);
    }

    public AddressReceiverNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public AddressReceiverNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
