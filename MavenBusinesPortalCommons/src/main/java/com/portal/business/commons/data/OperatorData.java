package com.portal.business.commons.data;

import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.exceptions.RegisterNotFoundException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.generic.WsRequest;
import com.portal.business.commons.models.Business;
import com.portal.business.commons.models.BPLanguage;
import com.portal.business.commons.models.Operator;
import com.portal.business.commons.models.BPPermission;
import com.portal.business.commons.models.BPProfile;
import com.portal.business.commons.utils.EjbConstants;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

/**
 *
 * @author hvarona
 */
public class OperatorData extends AbstractBusinessPortalWs {

    private static final Logger LOG = Logger.getLogger(OperatorData.class);

    public List<Operator> getOperatorList(Business commerce) throws EmptyListException, GeneralException {

        List<Operator> operatorList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Operator> cq = cb.createQuery(Operator.class);
            Root<Operator> from = cq.from(Operator.class);
            operatorList = entityManager.createQuery(cq.select(from).where(cb.equal(from.get("commerce"), commerce))).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (operatorList == null || operatorList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return operatorList;
    }

    public Operator getOperatorByLogin(String login) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Operator> cq = cb.createQuery(Operator.class);
            Root<Operator> from = cq.from(Operator.class);
            return entityManager.createQuery(cq.select(from).where(cb.equal(from.get("login"), login))).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Operator loadOperator(WsRequest request) throws RegisterNotFoundException, NullParameterException, GeneralException {
        return (Operator) loadEntity(Operator.class, request, LOG, getMethodName());
    }

    public Operator saveOperator(Operator operator) throws NullParameterException, GeneralException {
        if (operator == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "operator"), null);
        }
        return (Operator) saveEntity(operator);
    }

    public List<BPLanguage> getLanguageList() throws EmptyListException, GeneralException {

        List<BPLanguage> languageList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPLanguage> cq = cb.createQuery(BPLanguage.class);
            Root<BPLanguage> from = cq.from(BPLanguage.class);
            cq.select(from);

            languageList = entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (languageList == null || languageList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return languageList;
    }

    public List<BPProfile> getProfileList() throws EmptyListException, GeneralException {

        List<BPProfile> profileList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPProfile> cq = cb.createQuery(BPProfile.class);
            Root<BPProfile> from = cq.from(BPProfile.class);
            cq.select(from);
            cq.where(cb.equal(from.get("isOperator"), true));

            profileList = entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (profileList == null || profileList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return profileList;
    }

    public BPPermission loadPermission(Long permissionId) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (permissionId == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "permissionId"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPPermission> cq = cb.createQuery(BPPermission.class);
            Root<BPPermission> from = cq.from(BPPermission.class);

            cq.select(from).where(cb.equal(from.get("id"), permissionId));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (BPPermission) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("No se encontro el perfil");
        } catch (Exception ex) {
            ex.getMessage();
            throw new GeneralException("No se encontro el perfil");
        }
    }

}
