package com.portal.business.commons.generic;

import javax.ejb.Remote;

//INTERFAZ DE METODOS NO NECESARIAMENTE REMOTOS
@Remote
public interface RemittenceGenericEJB {

    void init();

    public boolean echo() throws Exception;
}
