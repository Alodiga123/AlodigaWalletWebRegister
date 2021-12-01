package com.portal.business.commons.models;

/**
 *
 * @author henry
 */
public class TransactionCommissionResponse {

    private int code;
    private String message;
    private float commissionValue;
    private boolean isPercentage;

    public TransactionCommissionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public TransactionCommissionResponse(int code, String message, float commissionValue, boolean isPercentage) {
        this.code = code;
        this.message = message;
        this.commissionValue = commissionValue;
        this.isPercentage = isPercentage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getCommissionValue() {
        return commissionValue;
    }

    public void setCommissionValue(float commissionValue) {
        this.commissionValue = commissionValue;
    }

    public boolean isIsPercentage() {
        return isPercentage;
    }

    public void setIsPercentage(boolean isPercentage) {
        this.isPercentage = isPercentage;
    }

}
