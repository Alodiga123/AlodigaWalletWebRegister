/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.portal.business.commons.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kgomez
 */
public class LocalServicesResponseCodes {

     public static final String SUCCESSFUL_OPERATION = "0";
    public static final String AUTHENTICATION_FAILED = "1";
    public static final String MISSING_PARAMETERS = "2";
    public static final String DISABLED_ACCOUNT = "3";
    public static final String INVALID_PHONE_NUMBER = "4";
    public static final String PIN_ALREADY_EXIST = "5";
    public static final String INVALID_AMOUNT = "6";
    public static final String BILLPAYMENTCODE_NOT_FOUND = "10";
    public static final String CARRIER_SYSTEM_UN_AVAILABLE_EXCEPTION = "13";
    public static final String TRANSACTION_COULD_BE_DUPLICATED = "18";
    public static final String INVALID_CREDIT_CARD_NUMBER = "23";
    public static final String INVALID_PAYMENT_INFO = "24";
    public static final String INVALID_CREDIT_CARD_DATE = "25";
    public static final String RECORDS_NOT_FOUND = "28";
    public static final String DISABLED_PIN = "29";
    public static final String TOPUP_TRANSACTION_EXCEPTION = "30";
    public static final String TOPUP_PRODUCT_NOT_AVAILABLE_EXCEPTION = "31";
    public static final String INVALID_FORMAT_EXCEPTION = "32";
    public static final String NOT_AVAILABLE_SERVICE_EXCEPTION = "33";
    public static final String DESTINATION_NOT_PREPAID_EXCEPTION = "34";
    public static final String AMOUNT_CONSUMED = "35";
    public static final String PAYMENT_DECLINED_EXCEPTION = "36";
    public static final String DUPLICATED_EXTERNAL_ID_EXCEPTION = "37";
    public static final String PIN_FREE_NOT_FOUND_EXCEPTION = "38";
    public static final String PIN_FREE_QUANTITY_EXCEEDED = "39";
    public static final String TRANSACTION_NOT_FOUND_FOR_EXTERNAL_ID = "40";
    public static final String MAX_NUMBER_TRANSACTION_DIALY_PER_DESTINATION = "41";
    public static final String MAX_NUMBER_TRANSACTION_DIALY_PER_SENDER = "42";
    public static final String INVALID_SUBSCRIBER_NUMBER = "43";
    public static final String SUBSCRIBER_WILL_EXCEED_LIMIT = "44";
    public static final String SUBSCRIBER_ACCOUNT_ERROR = "45";
    public static final String GENERAL_ERROR = "999";
    public static final Map<String, String> codes;

    static {
        codes = new HashMap<String, String>();
        codes.put(SUCCESSFUL_OPERATION, "Successful Operation.");
        codes.put(AUTHENTICATION_FAILED, "Authentication Failed.");
        codes.put(MISSING_PARAMETERS, "Missing parameters.");
        codes.put(DISABLED_ACCOUNT, "Disabled Account.");
        codes.put(RECORDS_NOT_FOUND, "Records not found.");
        codes.put(INVALID_AMOUNT, "Invalid amount.");
        codes.put(BILLPAYMENTCODE_NOT_FOUND, "BillPayment Service not found.");
        codes.put(TRANSACTION_COULD_BE_DUPLICATED, "Transaction could be duplicated.");
        codes.put(INVALID_CREDIT_CARD_NUMBER, "Invalid credit card number.");
        codes.put(INVALID_PAYMENT_INFO, "Invalid payment info.");
        codes.put(INVALID_CREDIT_CARD_DATE, "Invalid creditcard date.");
        codes.put(INVALID_PHONE_NUMBER, "Invalid Phone Number.");
        codes.put(PIN_ALREADY_EXIST, "Pin Already Exists.");
        codes.put(TOPUP_TRANSACTION_EXCEPTION, "TopUp Transaction.");
        codes.put(TOPUP_PRODUCT_NOT_AVAILABLE_EXCEPTION, "TopUp Product Not Available.");
        codes.put(INVALID_FORMAT_EXCEPTION, "Invalid Parameters Format.");
        codes.put(NOT_AVAILABLE_SERVICE_EXCEPTION, "Not Avaliable Service.");
        codes.put(DESTINATION_NOT_PREPAID_EXCEPTION, "Destination Not Prepaid.");
        codes.put(CARRIER_SYSTEM_UN_AVAILABLE_EXCEPTION, "Carrier System Un available. Try again later.");
        codes.put(DISABLED_PIN, "Pin is disabled.");
        codes.put(AMOUNT_CONSUMED, "Amount for pin has been consumed.");
        codes.put(PAYMENT_DECLINED_EXCEPTION, "Payment Declined.");
        codes.put(DUPLICATED_EXTERNAL_ID_EXCEPTION, "Duplicated externalId.");
        codes.put(PIN_FREE_NOT_FOUND_EXCEPTION, "Phone number(PinFree) Not found.");
        codes.put(PIN_FREE_QUANTITY_EXCEEDED, "The quantity of phone numbers to associate has been exceeded.");
        codes.put(TRANSACTION_NOT_FOUND_FOR_EXTERNAL_ID, "Transaction not found for externalId.");
        codes.put(MAX_NUMBER_TRANSACTION_DIALY_PER_DESTINATION, "The number of transaccion per day and destination has been exceeded.");
        codes.put(MAX_NUMBER_TRANSACTION_DIALY_PER_SENDER, "The number of transaccion per day and sender has been exceeded.");
        codes.put(INVALID_SUBSCRIBER_NUMBER, "Invalid subscriber number.");
        codes.put(SUBSCRIBER_WILL_EXCEED_LIMIT, "The Subscriber will exceed limit.");
        codes.put(SUBSCRIBER_ACCOUNT_ERROR, "The Account was not activated. a) Possible reasons are: Subscriber number is not found. b) Subscriber account is suspended c) Carrier is experienced internal error. Please try again.");
        codes.put(GENERAL_ERROR, "General Error.");
    }

}
