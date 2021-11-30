package com.portal.business.commons.cms;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "card")
public class CmsCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "sequentialNumber")
    private int sequentialNumber;

    @Column(name = "cardHolder")
    private String cardHolder;

    @Column(name = "issueDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date issueDate;

    @Column(name = "expirationDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "securityCodeCard")
    private String securityCodeCard;

    @Column(name = "securityCodeMagneticStrip")
    private String securityCodeMagneticStrip;

    @Column(name = "ICVVMagneticStrip")
    private String ICVVMagneticStrip;

    @ManyToOne
    @JoinColumn(name = "cardStatusId")
    private CmsCardStatus cardStatus;

    @Column(name = "pinOffset")
    private String pinOffset;

    @Column(name = "validationData")
    private String validationData;

    @Column(name = "pinLenght")
    private int pinLenght;

    @ManyToOne
    @JoinColumn(name = "personCustomerId")
    private CmsNaturalCustomer naturalCustomer;

    @Column(name = "automaticRenewalDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date automaticRenewalDate;

    @Column(name = "indRenewal")
    private Boolean indRenewal;

    @Column(name = "observations", length = 1500)
    private String observations;

    @Column(name = "statusUpdateReasonDate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date statusUpdateReasonDate;

    @Column(name = "indDeliveryRequest")
    private Boolean indDeliveryRequest;

    @Column(name = "createDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "updateDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;

    public CmsCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSequentialNumber() {
        return sequentialNumber;
    }

    public void setSequentialNumber(int sequentialNumber) {
        this.sequentialNumber = sequentialNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCodeCard() {
        return securityCodeCard;
    }

    public void setSecurityCodeCard(String securityCodeCard) {
        this.securityCodeCard = securityCodeCard;
    }

    public String getSecurityCodeMagneticStrip() {
        return securityCodeMagneticStrip;
    }

    public void setSecurityCodeMagneticStrip(String securityCodeMagneticStrip) {
        this.securityCodeMagneticStrip = securityCodeMagneticStrip;
    }

    public String getICVVMagneticStrip() {
        return ICVVMagneticStrip;
    }

    public void setICVVMagneticStrip(String ICVVMagneticStrip) {
        this.ICVVMagneticStrip = ICVVMagneticStrip;
    }

    public CmsCardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CmsCardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getPinOffset() {
        return pinOffset;
    }

    public void setPinOffset(String pinOffset) {
        this.pinOffset = pinOffset;
    }

    public String getValidationData() {
        return validationData;
    }

    public void setValidationData(String validationData) {
        this.validationData = validationData;
    }

    public int getPinLenght() {
        return pinLenght;
    }

    public void setPinLenght(int pinLenght) {
        this.pinLenght = pinLenght;
    }

    public Date getAutomaticRenewalDate() {
        return automaticRenewalDate;
    }

    public void setAutomaticRenewalDate(Date automaticRenewalDate) {
        this.automaticRenewalDate = automaticRenewalDate;
    }

    public Boolean getIndRenewal() {
        return indRenewal;
    }

    public void setIndRenewal(Boolean indRenewal) {
        this.indRenewal = indRenewal;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Date getStatusUpdateReasonDate() {
        return statusUpdateReasonDate;
    }

    public void setStatusUpdateReasonDate(Date statusUpdateReasonDate) {
        this.statusUpdateReasonDate = statusUpdateReasonDate;
    }

    public Boolean getIndDeliveryRequest() {
        return indDeliveryRequest;
    }

    public void setIndDeliveryRequest(Boolean indDeliveryRequest) {
        this.indDeliveryRequest = indDeliveryRequest;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public CmsNaturalCustomer getNaturalCustomer() {
        return naturalCustomer;
    }

    public void setNaturalCustomer(CmsNaturalCustomer naturalCustomer) {
        this.naturalCustomer = naturalCustomer;
    }

}
