package com.portal.business.commons.enumeration;

/**
 *
 * @author hvarona
 */
public enum OperationType {
    RECHARGE(true), 
    WITHDRAW(false), 
    REMITTANCE(true), 
    CARD_CONSULT(true), 
    CARD_ACTIVATED(true), 
    CARD_DEACTIVATED(true), 
    CARD_RECHARGE(true);

    private final boolean hasCashIn;

    private OperationType(boolean hasCashIn) {
        this.hasCashIn = hasCashIn;
    }

    public boolean isHasCashIn() {
        return hasCashIn;
    }

}
