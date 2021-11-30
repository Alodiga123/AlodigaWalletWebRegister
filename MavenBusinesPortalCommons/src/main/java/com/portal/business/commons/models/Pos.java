package com.portal.business.commons.models;

import com.portal.business.commons.exceptions.TableNotFoundException;
import com.portal.business.commons.generic.RemittenceEntity;
import com.portal.business.commons.utils.AlodigaCryptographyUtils;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author hvarona
 */
@Entity
@Table(name = "pos", uniqueConstraints = @UniqueConstraint(columnNames = {"storeId","posCode"}))

public class Pos extends RemittenceEntity implements Serializable {

    private final static DateFormat HOUR_FORMAT = new SimpleDateFormat("hh aa");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String posCode;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date openTime;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date closeTime;

    @Column(name = "enabled")
    private Boolean enabled;

    public Pos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
    }

    public Date getOpenTime() {
        return openTime;
    }

    public String getOpenTimeString() {
        return HOUR_FORMAT.format(openTime);
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public void setOpenTimeString(String openTimeString) {
        try {
            openTime = HOUR_FORMAT.parse(openTimeString);
        } catch (ParseException ex) {
            Logger.getLogger(Pos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public String getCloseTimeString() {
        return HOUR_FORMAT.format(closeTime);
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public void setCloseTimeString(String closeTimeString) {
        try {
            closeTime = HOUR_FORMAT.parse(closeTimeString);
        } catch (ParseException ex) {
            Logger.getLogger(Pos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Pos{" + "id=" + id + ", posCode=" + posCode + ", store=" + store + '}';
    }

    public String getQrText() {
        try {
            if (store == null) {
                return "";
            }
            String toEncrypt = store.getCommerce().getCode() + ";" + store.getStoreCode() + ";" + posCode;
            String value = AlodigaCryptographyUtils.encrypt(toEncrypt, "1nt3r4xt3l3ph0ny");
            return value;
        } catch (Exception ex) {
            Logger.getLogger(Pos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        if (!(obj instanceof Pos)) {
            return false;
        }
        final Pos other = (Pos) obj;
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
