/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.business.commons.models;

/**
 *
 * @author yamea
 */
public enum ResponseCode {
    SUCESS(0, "sucess"),
    MAX_TRANSACTION_AMOUNT_LIMIT_ID(1, "amount.dialy"),
    MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID(2, "amount.month"),
    MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID(3, "amoun.yearly"),
    MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID(4, "quantity.dialy"),
    MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID(5, "quantity.month"),
    MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID(6, "quantity.yearly"),
    MAX_NUMBER_OF_CARDS_ENABLED(7, "quantity.cards"),
    TRANSACTION_DISABLED(8, "transaction.disable"),
    INTERNAL_ERROR(9, "transaction.internalError");

    private final int codigo;
    private final String message;

    private ResponseCode(int codigo, String message) {
        this.codigo = codigo;
        this.message = message;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMessage() {
        return message;
    }

}
