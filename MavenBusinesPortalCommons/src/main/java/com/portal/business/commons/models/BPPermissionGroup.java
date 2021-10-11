package com.portal.business.commons.models;

import com.portal.business.commons.exceptions.TableNotFoundException;
import com.portal.business.commons.generic.RemittenceEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import javax.persistence.FetchType;

@Entity
@Table(name = "bppermission_group")
public class BPPermissionGroup extends RemittenceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean enabled;
    private String name;
    //bi-directional many-to-one association to PermissionGroupData
    @OneToMany(mappedBy = "permissionGroup", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<BPPermissionGroupData> permissionGroupData;

    public BPPermissionGroup() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BPPermissionGroupData> getPermissionGroupData() {
        return this.permissionGroupData;
    }

    public void setPermissionGroupData(List<BPPermissionGroupData> permissionGroupData) {
        this.permissionGroupData = permissionGroupData;
    }

    public BPPermissionGroupData getPermissionGroupDataByLanguageId(Long languageId) {

        for (BPPermissionGroupData pgData : this.permissionGroupData) {
            if (pgData.getLanguage().getId().equals(languageId)) {
                return pgData;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Object getPk() {
        return getId();
    }

    @Override
    public String getTableName() throws TableNotFoundException {
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
        final BPPermissionGroup other = (BPPermissionGroup) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
