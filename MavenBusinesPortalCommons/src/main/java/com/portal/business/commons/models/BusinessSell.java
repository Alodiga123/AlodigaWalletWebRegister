package com.portal.business.commons.models;

import com.portal.business.commons.enumeration.BPTransactionStatus;
import com.portal.business.commons.exceptions.TableNotFoundException;
import com.portal.business.commons.generic.RemittenceEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author henry
 */
@Entity
@Table(name = "business_sell")
public class BusinessSell extends RemittenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateSell")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateSell;

    @ManyToOne
    @JoinColumn(name = "idBusiness")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "idStore")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "idPos")
    private Pos pos;

    @Column(name = "amount")
    private float amount;

    @Column(name = "origin")
    private String origin;

    @Column(name = "idWalletTransaction")
    private long idWalletTransaction;

    @ManyToOne
    @JoinColumn(name = "idBusinessClose")
    private BusinessClose businessClose;

    @Column(name = "discountRate")
    private float discountRate;

    @Column(name = "idDiscountRate")
    private String idDiscountRate;

    @Column(name = "amountWithoutFee")
    private float amountWithoutFee;

    @Column(name = "status")
    private BPTransactionStatus transactionStatus;

    public BusinessSell() {
    }

    public BusinessSell(Date dateSell, Business business, float amount, String origin, long idWalletTransaction, BusinessClose businessClose) {
        this.dateSell = dateSell;
        this.business = business;
        this.amount = amount;
        this.origin = origin;
        this.idWalletTransaction = idWalletTransaction;
        this.businessClose = businessClose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateSell() {
        return dateSell;
    }

    public void setDateSell(Date dateSell) {
        this.dateSell = dateSell;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public long getIdWalletTransaction() {
        return idWalletTransaction;
    }

    public void setIdWalletTransaction(long idWalletTransaction) {
        this.idWalletTransaction = idWalletTransaction;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public BusinessClose getBusinessClose() {
        return businessClose;
    }

    public void setBusinessClose(BusinessClose businessClose) {
        this.businessClose = businessClose;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }

    public String getIdDiscountRate() {
        return idDiscountRate;
    }

    public void setIdDiscountRate(String idDiscountRate) {
        this.idDiscountRate = idDiscountRate;
    }

    public BPTransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(BPTransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public float getAmountWithoutFee() {
        return amountWithoutFee;
    }

    public void setAmountWithoutFee(float amountWithoutFee) {
        this.amountWithoutFee = amountWithoutFee;
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
