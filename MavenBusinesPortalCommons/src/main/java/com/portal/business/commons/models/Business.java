package com.portal.business.commons.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hvarona
 */
@Entity
@Table(name = "business")
@DiscriminatorValue("3")
public class Business extends BPUser {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "identifcation")
    private String identification;

    @Column(name = "commercialDenomination")
    private String commercialDenomination;

    @Column(name = "legalAddress")
    private String legalAddress;

    @Column(name = "address")
    private String address;

    @Column(name = "website")
    private String website;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private BusinessStatus status;

    @Column(name = "idPerson", nullable = true)
    private String idPerson;

    public Business() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getCommercialDenomination() {
        return commercialDenomination;
    }

    public void setCommercialDenomination(String commercialDenomination) {
        this.commercialDenomination = commercialDenomination;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public BusinessStatus getStatus() {
        return status;
    }

    public void setStatus(BusinessStatus status) {
        this.status = status;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

}
