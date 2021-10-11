package com.portal.business.commons.remittance;

import java.io.Serializable;

public class RemittanceAddress implements Serializable {

    private Long id;
    private String address;
    private String cityName;
    private String countryName;
    private String stateName;
    private String zipCode;

    private RemittanceCity city;
    private RemittanceState state;
    private RemittanceCountry country;

    public RemittanceAddress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public RemittanceCity getCity() {
        return city;
    }

    public void setCity(RemittanceCity city) {
        this.city = city;
    }

    public RemittanceState getState() {
        return state;
    }

    public void setState(RemittanceState state) {
        this.state = state;
    }

    public RemittanceCountry getCountry() {
        return country;
    }

    public void setCountry(RemittanceCountry country) {
        this.country = country;
    }

}
