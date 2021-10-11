package com.portal.business.commons.models;

import com.portal.business.commons.exceptions.TableNotFoundException;
import com.portal.business.commons.generic.RemittenceEntity;
import com.portal.business.commons.utils.QueryConstants;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.NamedQuery;

@Entity
@Table(name = "bppermission_has_profile")
@NamedQuery(name = QueryConstants.DELETE_PERMISSION_HAS_PROFILE,
query = "DELETE FROM BPPermissionHasProfile php WHERE php.profile.id=:profileId")
public class BPPermissionHasProfile extends RemittenceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //bi-directional many-to-one association to Permission
    @ManyToOne
    @JoinColumn(name = "permissionId")
    private BPPermission permission;
    //bi-directional many-to-one association to Profile
    @ManyToOne
    @JoinColumn(name = "profileId")
    private BPProfile profile;

    public BPPermissionHasProfile() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BPPermission getPermission() {
        return this.permission;
    }

    public void setPermission(BPPermission permission) {
        this.permission = permission;
    }

    public BPProfile getProfile() {
        return this.profile;
    }

    public void setProfile(BPProfile profile) {
        this.profile = profile;
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
        final BPPermissionHasProfile other = (BPPermissionHasProfile) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
