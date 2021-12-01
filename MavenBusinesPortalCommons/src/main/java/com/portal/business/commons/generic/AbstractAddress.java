/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.business.commons.generic;

import com.portal.business.commons.remittance.RemittanceAddress;
import java.util.List;
/**
 *
 * @author frank
 */
public abstract class AbstractAddress {
     
    public abstract boolean  deleteAddress(RemittanceAddress address);
    public abstract boolean updateAddress(RemittanceAddress address);
    public abstract List<RemittanceAddress> listAddress(RemittanceAddress address);
    public abstract RemittanceAddress findtAddress(RemittanceAddress address);
    public abstract boolean insertAddress(RemittanceAddress address); 
    
}
