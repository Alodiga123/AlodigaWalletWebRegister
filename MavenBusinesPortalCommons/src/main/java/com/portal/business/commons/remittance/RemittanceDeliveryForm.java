package com.portal.business.commons.remittance;

/**
 *
 * @author henry
 */
public class RemittanceDeliveryForm {

    private long id;

    private String name;

    public RemittanceDeliveryForm() {
    }

    public RemittanceDeliveryForm(long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final RemittanceDeliveryForm other = (RemittanceDeliveryForm) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
