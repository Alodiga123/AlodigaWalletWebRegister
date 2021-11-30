package com.portal.business.commons.models;

import com.portal.business.commons.enumeration.BPTransactionStatus;
import com.portal.business.commons.enumeration.OperationType;
import java.util.Date;

/**
 *
 * @author hvarona
 */
public class BusinessTransaction {

    private Long id;

    private Business business;

    private BusinessClose close;

    private BPUser user;

    private Date dateTransaction;

    private Long transactionId;

    private OperationType type;

    private float businessFee;

    private double totalAmount;

    private float businessCommission;

    private double amountWithoutFee;

    private BPTransactionStatus status;

    public BusinessTransaction(Business business, BusinessClose close, BPUser user,
            Date dateTransaction, Long transactionId, OperationType type,
            float businessFee, double totalAmount, double amountWithoutFee,
            BPTransactionStatus status) {
        this.business = business;
        this.close = close;
        this.user = user;
        this.dateTransaction = dateTransaction;
        this.transactionId = transactionId;
        this.type = type;
        this.businessFee = businessFee;
        this.totalAmount = totalAmount;
        this.amountWithoutFee = amountWithoutFee;
        this.status = status;
    }

    public BusinessTransaction(BusinessBalanceIncoming incoming) {
        this.id = incoming.getId();
        this.business = incoming.getBusiness();
        this.close = incoming.getClose();
        this.user = incoming.getUser();
        this.dateTransaction = incoming.getDateTransaction();
        this.transactionId = incoming.getTransactionId();
        this.type = incoming.getType();
        this.businessFee = incoming.getBusinessFee();
        this.totalAmount = incoming.getTotalAmount();
        this.amountWithoutFee = incoming.getAmountWithoutFee();
        this.businessCommission = incoming.getBusinessCommission();
        this.status = incoming.getTransactionStatus();
    }

    public BusinessTransaction(BusinessBalanceOutgoing outgoing) {
        this.id = outgoing.getId();
        this.business = outgoing.getBusiness();
        this.close = outgoing.getClose();
        this.user = outgoing.getUser();
        this.dateTransaction = outgoing.getDateTransaction();
        this.transactionId = outgoing.getTransactionId();
        this.type = outgoing.getType();
        this.businessFee = outgoing.getBusinessFee();
        this.totalAmount = outgoing.getTotalAmount();
        this.amountWithoutFee = outgoing.getAmountWithoutFee();
        this.businessCommission = outgoing.getBusinessCommission();
        this.status = outgoing.getTransactionStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public BusinessClose getClose() {
        return close;
    }

    public void setClose(BusinessClose close) {
        this.close = close;
    }

    public BPUser getUser() {
        return user;
    }

    public void setUser(BPUser user) {
        this.user = user;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public float getBusinessFee() {
        return businessFee;
    }

    public void setBusinessFee(float businessFee) {
        this.businessFee = businessFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmountWithoutFee() {
        return amountWithoutFee;
    }

    public void setAmountWithoutFee(double amountWithoutFee) {
        this.amountWithoutFee = amountWithoutFee;
    }

    public float getBusinessCommission() {
        return businessCommission;
    }

    public void setBusinessCommission(float businessCommission) {
        this.businessCommission = businessCommission;
    }

    public BPTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(BPTransactionStatus status) {
        this.status = status;
    }

}
