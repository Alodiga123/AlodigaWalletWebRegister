package com.portal.business.commons.models;

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
import javax.persistence.TemporalType;

/**
 *
 * @author hvarona
 */
@Entity
@Table(name = "business_close")
public class BusinessClose extends RemittenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "businessId", nullable = false)
    private Business business;

    @Column(name = "dateClose")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClose;

    @Column(name = "status")
    private CloseStatus status;
    
    

    public static enum CloseStatus {
        PENDING, PROCESSED;
    }

    public BusinessClose() {
    }

    public BusinessClose(Business business, Date dateClose, CloseStatus status) {
        this.business = business;
        this.dateClose = dateClose;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public CloseStatus getStatus() {
        return status;
    }

    public void setStatus(CloseStatus status) {
        this.status = status;
    }

    @Override
    public Object getPk() {
        return this.getId();
    }

    @Override
    public String getTableName() throws TableNotFoundException {
        return super.getTableName(BusinessClose.class);
    }

}
