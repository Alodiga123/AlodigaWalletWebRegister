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
@Table(name = "naturalCustomer")
public class CmsNaturalCustomer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificationNumber", length = 40)
    private String identificationNumber;

    @Column(name = "dueDateDocumentIdentification")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dueDateDocumentIdentification;

    @Column(name = "indNaturalized")
    private Boolean indNaturalized;

    @Column(name = "identificationNumberOld")
    private String identificationNumberOld;

    @ManyToOne
    @JoinColumn(name = "statusCostumerId")
    private CmsStatusCustomer statusCostumer;

    @Column(name = "indForeign")
    private Boolean indForeign;

    @Column(name = "countryStayTime")
    private int countryStayTime;

    @Column(name = "firstNames")
    private String firstNames;

    @Column(name = "lastNames")
    private String lastNames;

    @Column(name = "marriedLastNames")
    private String marriedLastNames;

    @Column(name = "rifNumber")
    private String rifNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "placeBirth")
    private String placeBirth;

    @Column(name = "dateBirth")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBirth;

    @Column(name = "familyResponsabilities")
    private String familyResponsabilities;

    @ManyToOne
    @JoinColumn(name = "naturalCostumerId")
    private CmsStatusCustomer naturalCostumer;

    @Column(name = "createDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "cardNumber")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;

    public CmsNaturalCustomer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Date getDueDateDocumentIdentification() {
        return dueDateDocumentIdentification;
    }

    public void setDueDateDocumentIdentification(Date dueDateDocumentIdentification) {
        this.dueDateDocumentIdentification = dueDateDocumentIdentification;
    }

    public Boolean getIndNaturalized() {
        return indNaturalized;
    }

    public void setIndNaturalized(Boolean indNaturalized) {
        this.indNaturalized = indNaturalized;
    }

    public String getIdentificationNumberOld() {
        return identificationNumberOld;
    }

    public void setIdentificationNumberOld(String identificationNumberOld) {
        this.identificationNumberOld = identificationNumberOld;
    }

    public CmsStatusCustomer getStatusCostumer() {
        return statusCostumer;
    }

    public void setStatusCostumer(CmsStatusCustomer statusCostumer) {
        this.statusCostumer = statusCostumer;
    }

    public Boolean getIndForeign() {
        return indForeign;
    }

    public void setIndForeign(Boolean indForeign) {
        this.indForeign = indForeign;
    }

    public int getCountryStayTime() {
        return countryStayTime;
    }

    public void setCountryStayTime(int countryStayTime) {
        this.countryStayTime = countryStayTime;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getMarriedLastNames() {
        return marriedLastNames;
    }

    public void setMarriedLastNames(String marriedLastNames) {
        this.marriedLastNames = marriedLastNames;
    }

    public String getRifNumber() {
        return rifNumber;
    }

    public void setRifNumber(String rifNumber) {
        this.rifNumber = rifNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getFamilyResponsabilities() {
        return familyResponsabilities;
    }

    public void setFamilyResponsabilities(String familyResponsabilities) {
        this.familyResponsabilities = familyResponsabilities;
    }

    public CmsStatusCustomer getNaturalCostumer() {
        return naturalCostumer;
    }

    public void setNaturalCostumer(CmsStatusCustomer naturalCostumer) {
        this.naturalCostumer = naturalCostumer;
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

}
