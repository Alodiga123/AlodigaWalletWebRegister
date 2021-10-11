package com.portal.business.commons.remittance;

import com.portal.business.commons.exceptions.TableNotFoundException;

public class RemittanceState {

    private long id;
    private String name;
    private RemittanceCountry country;

    public RemittanceState() {
    }

    public RemittanceState(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RemittanceCountry getCountry() {
        return this.country;
    }

    public void setCountry(RemittanceCountry country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RemittanceState other = (RemittanceState) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
