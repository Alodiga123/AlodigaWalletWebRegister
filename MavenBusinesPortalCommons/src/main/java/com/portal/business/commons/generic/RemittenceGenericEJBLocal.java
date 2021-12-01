package com.portal.business.commons.generic;

import javax.ejb.Local;

@Local
public interface RemittenceGenericEJBLocal
{
	void init();
    public boolean echo() throws Exception;
}
