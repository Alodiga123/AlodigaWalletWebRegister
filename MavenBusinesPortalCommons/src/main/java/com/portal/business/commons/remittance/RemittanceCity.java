package com.portal.business.commons.remittance;

/**
 * The persistent class for the city database table.
 *
 */
public class RemittanceCity {

    private long id;
    private String name;
    private RemittanceState state;

    public RemittanceCity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RemittanceState getState() {
        return state;
    }

    public void setState(RemittanceState state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final RemittanceCity other = (RemittanceCity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
