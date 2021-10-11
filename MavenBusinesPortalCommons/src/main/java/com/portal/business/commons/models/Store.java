package com.portal.business.commons.models;

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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "store", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idCommerce", "name"})
    ,@UniqueConstraint(columnNames = {"idCommerce", "storeCode"})})
public class Store extends RemittenceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "code")
    private String storeCode;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "opentime")
    private Date openTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "closetime")
    private Date closeTime;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "idCommerce")
    private Business commerce;

    public Store() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Business getCommerce() {
        return commerce;
    }

    public void setCommerce(Business commerce) {
        this.commerce = commerce;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object getPk() {
        return getId();
    }

    @Override
    public String getTableName() {
        return super.getTableName(this.getClass());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Store other = (Store) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
