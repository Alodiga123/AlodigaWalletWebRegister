package com.portal.business.commons.models;

import com.portal.business.commons.exceptions.TableNotFoundException;
import com.portal.business.commons.generic.RemittenceEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bpprofile")
public class BPProfile extends RemittenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "name")
    private String name;

    @Column(name = "isOperator")
    private boolean isOperator;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List<BPPermissionHasProfile> permissionHasProfiles;
    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<BPProfileData> profileData;

    public BPProfile() {
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

    public boolean isIsOperator() {
        return isOperator;
    }

    public void setIsOperator(boolean isOperator) {
        this.isOperator = isOperator;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BPPermissionHasProfile> getPermissionHasProfiles() {
        return this.permissionHasProfiles;
    }

    public void setPermissionHasProfiles(List<BPPermissionHasProfile> permissionHasProfiles) {
        this.permissionHasProfiles = permissionHasProfiles;
    }

    public List<BPProfileData> getProfileData() {
        return this.profileData;
    }

    public void setProfileData(List<BPProfileData> profileData) {
        this.profileData = profileData;
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

    public BPProfileData getProfileDataByLanguageId(Long languageId) {
        BPProfileData pd = null;
        for (BPProfileData pData : this.profileData) {
            if (pData.getLanguage().getId().equals(languageId)) {
                pd = pData;
                break;
            }
        }
        return pd;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof BPProfile) && (id != null)
                ? id.equals(((BPProfile) other).id)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

}
