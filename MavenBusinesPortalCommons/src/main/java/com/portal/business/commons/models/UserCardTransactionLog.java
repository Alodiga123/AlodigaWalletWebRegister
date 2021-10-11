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
 * @author hvarona
 */
@Entity
@Table(name = "user_card_transaction_log")
public class UserCardTransactionLog extends RemittenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn
    private BPUser user;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "createdDate")
    private Date createdDate;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "datelog")
    private Date dateLog;

    @Column(name = "transactionAction")
    private TransactionAction transactionAction;

    @Column(name = "success")
    private Boolean success;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "status")
    private BPTransactionStatus transactionStatus;

    public static enum TransactionAction {

        CONSULT("consult"),
        ACTIVATE("activate"),
        DEACTIVATE("deactivate");

        private String label;

        private TransactionAction(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

    }

    public UserCardTransactionLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BPUser getUser() {
        return user;
    }

    public void setUser(BPUser user) {
        this.user = user;
    }

    public Date getDateLog() {
        return dateLog;
    }

    public void setDateLog(Date dateLog) {
        this.dateLog = dateLog;
    }

    public TransactionAction getTransactionAction() {
        return transactionAction;
    }

    public void setTransactionAction(TransactionAction transactionAction) {
        this.transactionAction = transactionAction;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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
    
    

    @Override
    public Object getPk() {
        return this.getId();
    }

    @Override
    public String getTableName() throws TableNotFoundException {
        return super.getTableName(this.getClass());
    }

}
