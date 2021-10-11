package com.portal.business.commons.data;

import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.models.Business;
import com.portal.business.commons.models.BusinessSell;
import com.portal.business.commons.models.Pos;
import com.portal.business.commons.models.Store;
import com.portal.business.commons.utils.EjbConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

/**
 *
 * @author henry
 */
public class BusinessSellData extends AbstractBusinessPortalWs {

    private static final Logger LOG = Logger.getLogger(BusinessSellData.class);

    public BusinessSell saveBusinessSell(BusinessSell businessSell) throws NullParameterException, GeneralException {
        if (businessSell == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "business"), null);
        }
        return (BusinessSell) saveEntity(businessSell);
    }

    public List<BusinessSell> getBusinessSales(Business business, Date startDate, Date endDate) throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessSell> cq = cb.createQuery(BusinessSell.class);
            Root<BusinessSell> from = cq.from(BusinessSell.class);
            cq.select(from);

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateSell");

            predList.add(cb.equal(from.get("business"), business));
            predList.add(cb.between(dateEntryPath, startDate, endDate));

            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            Query query = entityManager.createQuery(cq);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public BusinessSell getBusinessSell(Business business, String id) throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessSell> cq = cb.createQuery(BusinessSell.class);
            Root<BusinessSell> from = cq.from(BusinessSell.class);
            cq.select(from);

            cq.where(cb.equal(from.get("business"), business),
                    cb.equal(from.get("id"), id));
            Query query = entityManager.createQuery(cq);

            return (BusinessSell) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public Long getBusinessSalesNumber(Business business, Date startDate, Date endDate) throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<BusinessSell> from = cq.from(BusinessSell.class);
            cq.select(cb.count(from));

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateSell");

            predList.add(cb.equal(from.get("business"), business));
            predList.add(cb.between(dateEntryPath, startDate, endDate));

            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            Query query = entityManager.createQuery(cq);

            return (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<BusinessSell> getBusinessSales(Store store, Date startDate, Date endDate) throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessSell> cq = cb.createQuery(BusinessSell.class);
            Root<BusinessSell> from = cq.from(BusinessSell.class);
            cq.select(from);

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateSell");

            predList.add(cb.equal(from.get("store"), store));
            predList.add(cb.between(dateEntryPath, startDate, endDate));

            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            Query query = entityManager.createQuery(cq);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<BusinessSell> getBusinessSales(Pos pos, Date startDate, Date endDate) throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessSell> cq = cb.createQuery(BusinessSell.class);
            Root<BusinessSell> from = cq.from(BusinessSell.class);
            cq.select(from);

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateSell");

            predList.add(cb.equal(from.get("pos"), pos));
            predList.add(cb.between(dateEntryPath, startDate, endDate));

            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            Query query = entityManager.createQuery(cq);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }
}
