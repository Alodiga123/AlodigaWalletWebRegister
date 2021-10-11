package com.portal.business.commons.enumeration;

/**
 *
 * @author henry
 */
public enum BusinessServiceType {
    RECHARGE("PROREC", true,OperationType.RECHARGE),
    WITHDRAW("PROWIT", true,OperationType.WITHDRAW),
    REMITTANCE("TRAREM", true,OperationType.REMITTANCE),
    CARD_CONSULT("CARCON", false,OperationType.CARD_CONSULT),
    CARD_ACTIVATION("CARENA", false,OperationType.CARD_ACTIVATED),
    CARD_RECHARGE("PROREC", true,OperationType.CARD_RECHARGE);

    private final String preferenceName;
    private final boolean hasAmount;
    private final OperationType operationType;

    private BusinessServiceType(String preferenceName, boolean hasAmount, OperationType operationType) {
        this.preferenceName = preferenceName;
        this.hasAmount = hasAmount;
        this.operationType = operationType;
    }

    public String getPreferenceName() {
        return preferenceName;
    }

    public boolean isHasAmount() {
        return hasAmount;
    }

    public OperationType getOperationType() {
        return operationType;
    }
    
    

}
