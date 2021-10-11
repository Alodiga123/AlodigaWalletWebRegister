package com.portal.business.commons.models;

import com.portal.business.commons.enumeration.BPTransactionStatus;
import com.portal.business.commons.enumeration.OperationType;
import com.portal.business.commons.exceptions.TableNotFoundException;
import com.portal.business.commons.generic.RemittenceEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hvarona
 */
@Entity
@Table(name = "business_balance_in")
public class BusinessBalanceIncoming extends RemittenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "businessId", nullable = false)
    private Business business;

    @ManyToOne
    @JoinColumn(name = "closeId", nullable = true)
    private BusinessClose close;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private BPUser user;

    @Column(name = "dateTransaction")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransaction;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "transactionId")
    private Long transactionId;

    @Column(name = "type")
    private OperationType type;

    @Column(name = "businessFee")
    private float businessFee;

    @Column(name = "totalAmount")
    private double totalAmount;

    @Column(name = "amountWithoutFee")
    private double amountWithoutFee;

    @Column(name = "businessCommission")
    private float businessCommission;

    @Column(name = "status")
    private BPTransactionStatus transactionStatus;

    public BusinessBalanceIncoming() {
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

    public BPTransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(BPTransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public float getBusinessCommission() {
        return businessCommission;
    }

    public void setBusinessCommission(float businessCommission) {
        this.businessCommission = businessCommission;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BusinessBalanceIncoming other = (BusinessBalanceIncoming) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public Object getPk() {
        return getId();
    }

    @Override
    public String getTableName() throws TableNotFoundException {
        return super.getTableName(this.getClass());
    }

}
