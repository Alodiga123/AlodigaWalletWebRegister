package com.portal.business.commons.data;

import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.exceptions.RegisterNotFoundException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.generic.WsRequest;
import com.portal.business.commons.models.BPLanguage;
import com.portal.business.commons.utils.EjbConstants;
import java.util.List;
import java.util.Random;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

/**
 *
 * @author usuario
 */
public class UtilsData extends AbstractBusinessPortalWs {

    private static final String dCase = "abcdefghijklmnopqrstuvwxyz";
    private static final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String intChar = "0123456789";
    private static Random r = new Random();
    private static String pass = "";
    private static final Logger logger = Logger.getLogger(UtilsData.class);

    public List<BPLanguage> getLanguages() throws EmptyListException, GeneralException, NullParameterException {
        WsRequest request = new WsRequest();
        List<BPLanguage> languages = (List<BPLanguage>) listEntities(BPLanguage.class, request, logger, getMethodName());
        return languages;
    }

    public List<BPLanguage> getLanguages(WsRequest request) throws GeneralException, EmptyListException, NullParameterException {
        return (List<BPLanguage>) listEntities(BPLanguage.class, request, logger, getMethodName());
    }

    public BPLanguage loadLanguage(WsRequest request) throws RegisterNotFoundException, NullParameterException, GeneralException {
        BPLanguage language = (BPLanguage) loadEntity(BPLanguage.class, request, logger, getMethodName());
        return language;
    }

    public BPLanguage getLanguage(String isoName) throws RegisterNotFoundException, NullParameterException, GeneralException {
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPLanguage> cq = cb.createQuery(BPLanguage.class);
            Root<BPLanguage> from = cq.from(BPLanguage.class);
            cq.select(from);
            cq.where(
                    cb.equal(from.get("iso"), isoName));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (BPLanguage) query.getSingleResult();
        } catch (Exception ex) {
            throw new GeneralException(logger, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), ex.getMessage()), null);
        }
    }

    public String generatedKey(int length) {
        while (pass.length() != 16) {
            int rPick = r.nextInt(4);
            switch (rPick) {
                case 0:
                    {
                        int spot = r.nextInt(25);
                        pass += dCase.charAt(spot);
                        break;
                    }
                case 1:
                    {
                        int spot = r.nextInt(25);
                        pass += uCase.charAt(spot);
                        break;
                    }
                case 3:
                    {
                        int spot = r.nextInt(9);
                        pass += intChar.charAt(spot);
                        break;
                    }
                default:
                    break;
            }
        }
        return pass;
    }
}
