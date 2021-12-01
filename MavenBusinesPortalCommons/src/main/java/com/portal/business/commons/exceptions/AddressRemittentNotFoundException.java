package com.portal.business.commons.exceptions;

import org.apache.log4j.Logger;

public class AddressRemittentNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public AddressRemittentNotFoundException(String message) {
        super(message);
    }

    public AddressRemittentNotFoundException(Logger logger, String message, Exception e) {
        super(message, e);
        logger.error(message, e);
    }

    public AddressRemittentNotFoundException(String message, StackTraceElement[] stackTrace) {
        super(message);
        setStackTrace(stackTrace);
    }
}
