package com.portal.business.commons.data;

import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.exceptions.RegisterNotFoundException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.generic.WsRequest;
import com.portal.business.commons.models.Business;
import com.portal.business.commons.models.Store;
import com.portal.business.commons.utils.EjbConstants;
import org.apache.log4j.Logger;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author usuario
 */
public class StoreData extends AbstractBusinessPortalWs {

    private static final Logger logger = Logger.getLogger(StoreData.class);

    public List<Store> getStores(Business commerce) throws EmptyListException, GeneralException, NullParameterException {
        List<Store> stores = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Store> cq = cb.createQuery(Store.class);
            Root<Store> from = cq.from(Store.class);
            cq.select(from).where(cb.equal(from.get("commerce"), commerce));

            Query query = entityManager.createQuery(cq);
            stores = query.getResultList();
        } catch (Exception e) {
            throw new GeneralException(logger, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (stores == null || stores.isEmpty()) {
            throw new EmptyListException(logger, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return stores;

    }

    public List<Store> getEnabledStores(Business commerce) throws EmptyListException, GeneralException, NullParameterException {
        List<Store> stores = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Store> cq = cb.createQuery(Store.class);
            Root<Store> from = cq.from(Store.class);
            cq.select(from).where(
                    cb.equal(from.get("commerce"), commerce),
                    cb.equal(from.get("enabled"), true));

            Query query = entityManager.createQuery(cq);
            stores = query.getResultList();
        } catch (Exception e) {
            throw new GeneralException(logger, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (stores == null || stores.isEmpty()) {
            throw new EmptyListException(logger, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return stores;

    }

    public Store getStore(Long storeId) throws GeneralException, RegisterNotFoundException, NullParameterException {
        if (storeId == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "storeId"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Store> cq = cb.createQuery(Store.class);
            Root<Store> from = cq.from(Store.class);
            cq.select(from);
            cq.where(cb.equal(from.get("id"), storeId));

            Query query = entityManager.createQuery(cq);
            return (Store) query.getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(logger, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public Store loadStore(WsRequest request) throws GeneralException, RegisterNotFoundException, NullParameterException {
        return (Store) loadEntity(Store.class, request, logger, getMethodName());
    }

    public Store saveStore(Store store) throws NullParameterException, GeneralException {
        if (store == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "store"), null);
        }
        return (Store) saveEntity(store);
    }
    
    public Store getStoreByCode(String code) throws NullParameterException, GeneralException {
        if (code == null) {
            throw new NullParameterException(logger, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "code"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Store> cq = cb.createQuery(Store.class);
            Root<Store> from = cq.from(Store.class);
            cq.select(from).where(cb.equal(from.get("storeCode"), code));
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(logger, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }
}
