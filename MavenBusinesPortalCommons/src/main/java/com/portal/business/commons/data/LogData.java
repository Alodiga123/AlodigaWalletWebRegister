package com.portal.business.commons.data;

import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.models.UserCardTransactionLog;
import com.portal.business.commons.utils.EjbConstants;
import org.apache.log4j.Logger;

/**
 *
 * @author hvarona
 */
public class LogData extends AbstractBusinessPortalWs {

    private static final Logger LOG = Logger.getLogger(LogData.class);

    public UserCardTransactionLog saveUserCardTransactionLog(UserCardTransactionLog log) throws NullParameterException, GeneralException {
        if (log == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "log"), null);
        }
        return (UserCardTransactionLog) saveEntity(log);
    }

}
