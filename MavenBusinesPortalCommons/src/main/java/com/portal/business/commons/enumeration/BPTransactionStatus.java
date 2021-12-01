package com.portal.business.commons.enumeration;

/**
 *
 * @author henry
 */
public enum BPTransactionStatus {
    CREATED("CREATED"),
    IN_PROCESS("IN_PROCESS"),
    FAILED("FAILED"),
    COMPLETED("COMPLETED");

    private final String code;

    BPTransactionStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
