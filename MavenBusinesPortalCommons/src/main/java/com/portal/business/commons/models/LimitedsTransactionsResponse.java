package com.portal.business.commons.models;


/**
 * Class for limits transactional response
 *
 */
public class LimitedsTransactionsResponse {

    private int code;
    private String message;
    private boolean valid;

    public LimitedsTransactionsResponse() {
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

   

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    

}
