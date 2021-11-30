package com.portal.business.commons.models;

public enum PreferenceFieldEnum {

    TIMEOUT_INACTIVE_SESSION_ID(1L, PreferenceTypeValuesEnum.INTEGER),
    MAX_TRANSACTION_AMOUNT_LIMIT_ID(4L, PreferenceTypeValuesEnum.FLOAT),
    MAX_WRONG_LOGIN_INTENT_NUMBER_ID(7L, PreferenceTypeValuesEnum.INTEGER),
    DISABLED_TRANSACTION_ID(9l, PreferenceTypeValuesEnum.INTEGER),
    MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID(10L, PreferenceTypeValuesEnum.FLOAT),
    DEFAULT_SMS_PROVIDER_ID(12L, PreferenceTypeValuesEnum.INTEGER),
    MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID(20L, PreferenceTypeValuesEnum.FLOAT),
    MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID(21L, PreferenceTypeValuesEnum.FLOAT),
    MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID(22L, PreferenceTypeValuesEnum.INTEGER),
    MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID(23l, PreferenceTypeValuesEnum.INTEGER),
    MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID(24l, PreferenceTypeValuesEnum.INTEGER),
    MAX_NUMBER_OF_CARDS_ENABLED(25l, PreferenceTypeValuesEnum.INTEGER);

    private Long id;
    private PreferenceTypeValuesEnum type;

    private PreferenceFieldEnum(Long id, PreferenceTypeValuesEnum preferenceTypeValues) {
        this.id = id;
        this.type = preferenceTypeValues;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setType(PreferenceTypeValuesEnum type) {
        this.type = type;
    }

    public PreferenceTypeValuesEnum getType() {

        return type;

    }
}
