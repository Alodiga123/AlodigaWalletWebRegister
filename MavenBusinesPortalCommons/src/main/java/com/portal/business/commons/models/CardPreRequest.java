package com.portal.business.commons.models;

import com.portal.business.commons.cms.CmsCity;
import com.portal.business.commons.cms.CmsCountry;
import com.portal.business.commons.cms.CmsDocumentPersonType;
import com.portal.business.commons.cms.CmsState;
import com.portal.business.commons.exceptions.TableNotFoundException;
import com.portal.business.commons.generic.RemittenceEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author henry
 */
@Entity
@Table(name = "card_pre_request")
public class CardPreRequest extends RemittenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "idDocumentType")
    private int idDocumentType;

    @Column(name = "identificationNumber")
    private String identificationNumber;

    @Column(name = "birthdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthdate;

    @Column(name = "sex")
    private char sex;

    @Column(name = "maritalStatus")
    private String maritalStatus;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "idCountry")
    private int idCountry;

    @Column(name = "idState")
    private int idState;

    @Column(name = "idCity")
    private int idCity;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "hasPromotions")
    private boolean promotions;

    @Column(name = "isAmericanCitizen")
    private boolean americanCitizen;

    @Column(name = "sendInfo")
    private CardPreRequestSendInfo sendInfo;

    @Column(name = "dateRequest")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateRequest;

    @Transient
    private CmsCountry country;

    @Transient
    private CmsCity city;

    @Transient
    private CmsState state;

    @Transient
    private CmsDocumentPersonType documentType;

    @Column(name = "status")
    private CardPreRequestStatus status;

    public enum CardPreRequestStatus {
        PREREQUEST, PENDING, APROVED
    }

    public enum CardPreRequestSendInfo {
        SMS, EMAIL, BOTH;
    }

    public CardPreRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isPromotions() {
        return promotions;
    }

    public void setPromotions(boolean promotions) {
        this.promotions = promotions;
    }

    public boolean isAmericanCitizen() {
        return americanCitizen;
    }

    public void setAmericanCitizen(boolean americanCitizen) {
        this.americanCitizen = americanCitizen;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CmsCountry getCountry() {
        return country;
    }

    public void setCountry(CmsCountry country) {
        this.country = country;
        if (country != null) {
            this.idCountry = country.getId();
        }
    }

    public CmsCity getCity() {
        return city;
    }

    public void setCity(CmsCity city) {
        this.city = city;
        if (city != null) {
            this.idCity = city.getId();
        }
    }

    public CmsState getState() {
        return state;
    }

    public void setState(CmsState state) {
        this.state = state;
        if (state != null) {
            this.idState = state.getId();
        }
    }

    public int getIdDocumentType() {
        return idDocumentType;
    }

    public CardPreRequestStatus getStatus() {
        return status;
    }

    public void setStatus(CardPreRequestStatus status) {
        this.status = status;
    }

    public void setIdDocumentType(int idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public CmsDocumentPersonType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(CmsDocumentPersonType documentType) {
        this.documentType = documentType;
        if (documentType != null) {
            this.idDocumentType = documentType.getId();
        }
    }

    public CardPreRequestSendInfo getSendInfo() {
        return sendInfo;
    }

    public void setSendInfo(CardPreRequestSendInfo sendInfo) {
        this.sendInfo = sendInfo;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final CardPreRequest other = (CardPreRequest) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
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
