package com.portal.business.commons.cms;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author henry
 */
@Entity
@Table(name = "personType")
public class CmsPersonType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private CmsCountry country;

    @Column(name = "originApplicationId")
    private int originApplicationId;

    @Column(name = "indNaturalPerson")
    private boolean indNaturalPerson;

    public CmsPersonType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CmsCountry getCountry() {
        return country;
    }

    public void setCountry(CmsCountry country) {
        this.country = country;
    }

    public int getOriginApplicationId() {
        return originApplicationId;
    }

    public void setOriginApplicationId(int originApplicationId) {
        this.originApplicationId = originApplicationId;
    }

    public boolean isIndNaturalPerson() {
        return indNaturalPerson;
    }

    public void setIndNaturalPerson(boolean indNaturalPerson) {
        this.indNaturalPerson = indNaturalPerson;
    }

}
