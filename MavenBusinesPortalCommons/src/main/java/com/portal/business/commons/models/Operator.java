package com.portal.business.commons.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hvarona
 */
@Entity
@Table(name = "operator")
@DiscriminatorValue("2")
public class Operator extends BPUser {

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lasttname")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "idCommerce")
    private Business commerce;

    @ManyToMany
    @JoinTable(
            name = "operator_exclude_permission",
            joinColumns = @JoinColumn(name = "idPermission"),
            inverseJoinColumns = @JoinColumn(name = "idOperator")
    )
    private List<BPPermission> excludedPermission;
    
    
    @ManyToOne
    @JoinColumn(name = "idStore",nullable = true)
    private Store store;
    
    @ManyToOne
    @JoinColumn(name = "idPos",nullable = true)
    private Pos pos;

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

    public Business getCommerce() {
        return commerce;
    }

    public void setCommerce(Business commerce) {
        this.commerce = commerce;
    }

    public List<BPPermission> getExcludedPermission() {
        return excludedPermission;
    }

    public void setExcludedPermission(List<BPPermission> excludedPermission) {
        this.excludedPermission = excludedPermission;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }
    
    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName();
    }

}
