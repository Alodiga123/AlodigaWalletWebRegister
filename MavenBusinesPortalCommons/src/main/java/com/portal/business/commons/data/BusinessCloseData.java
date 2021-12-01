package com.portal.business.commons.data;

import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.models.Business;
import com.portal.business.commons.models.BusinessBalanceIncoming;
import com.portal.business.commons.models.BusinessBalanceOutgoing;
import com.portal.business.commons.models.BusinessClose;
import com.portal.business.commons.models.BusinessSell;
import com.portal.business.commons.models.BusinessTransaction;
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
public class BusinessCloseData extends AbstractBusinessPortalWs {

    private static final Logger LOG = Logger.getLogger(BusinessCloseData.class);

    public synchronized BusinessClose closeBusiness(Business business) throws GeneralException, NullParameterException {

        List<BusinessBalanceIncoming> incomings = getPendingIncomingBalance(business);
        List<BusinessBalanceOutgoing> outcomings = getPendingOutcomingBalance(business);
        Date dateClose = new Date();

        BusinessClose businessClose = new BusinessClose(business, dateClose, BusinessClose.CloseStatus.PENDING);
        businessClose = (BusinessClose) saveEntity(businessClose);

        for (BusinessBalanceIncoming incoming : incomings) {
            incoming.setClose(businessClose);
            saveEntity(incoming);
        }

        for (BusinessBalanceOutgoing outcoming : outcomings) {
            outcoming.setClose(businessClose);
            saveEntity(outcoming);
        }

        List<BusinessSell> sells = getPendingBusinessSell(business);

        for (BusinessSell sell : sells) {
            sell.setBusinessClose(businessClose);
            saveEntity(sell);
        }

        return businessClose;
    }

    public synchronized BusinessClose closeBusiness(Business business, Date dateClose) throws GeneralException, NullParameterException {

        List<BusinessBalanceIncoming> incomings = getPendingIncomingBalance(business, dateClose);
        List<BusinessBalanceOutgoing> outcomings = getPendingOutcomingBalance(business, dateClose);

        BusinessClose businessClose = new BusinessClose(business, dateClose, BusinessClose.CloseStatus.PENDING);
        businessClose = (BusinessClose) saveEntity(businessClose);

        for (BusinessBalanceIncoming incoming : incomings) {
            incoming.setClose(businessClose);
            saveEntity(incoming);
        }

        for (BusinessBalanceOutgoing outcoming : outcomings) {
            outcoming.setClose(businessClose);
            saveEntity(outcoming);
        }

        List<BusinessSell> sells = getPendingBusinessSell(business, dateClose);

        for (BusinessSell sell : sells) {
            sell.setBusinessClose(businessClose);
            saveEntity(sell);
        }

        return businessClose;
    }

    private List<BusinessBalanceIncoming> getPendingIncomingBalance(Business business) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceIncoming> cq = cb.createQuery(BusinessBalanceIncoming.class);
            Root<BusinessBalanceIncoming> from = cq.from(BusinessBalanceIncoming.class);
            cq.select(from);
            cq.where(cb.equal(from.get("business"), business),
                    cb.isNull(from.get("close")));

            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
        }
        return new ArrayList();
    }

    private List<BusinessBalanceOutgoing> getPendingOutcomingBalance(Business business) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceOutgoing> cq = cb.createQuery(BusinessBalanceOutgoing.class);
            Root<BusinessBalanceOutgoing> from = cq.from(BusinessBalanceOutgoing.class);
            cq.select(from);
            cq.where(cb.equal(from.get("business"), business),
                    cb.isNull(from.get("close")));

            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
        }
        return new ArrayList();
    }

    private List<BusinessBalanceIncoming> getPendingIncomingBalance(Business business, Date dateClose) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceIncoming> cq = cb.createQuery(BusinessBalanceIncoming.class);
            Root<BusinessBalanceIncoming> from = cq.from(BusinessBalanceIncoming.class);
            cq.select(from);
            cq.where(cb.equal(from.get("business"), business),
                    cb.isNull(from.get("close")),
                    cb.lessThan(from.<Date>get("dateTransaction"), dateClose));

            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
        }
        return new ArrayList();
    }

    private List<BusinessBalanceOutgoing> getPendingOutcomingBalance(Business business, Date dateClose) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceOutgoing> cq = cb.createQuery(BusinessBalanceOutgoing.class);
            Root<BusinessBalanceOutgoing> from = cq.from(BusinessBalanceOutgoing.class);
            cq.select(from);
            cq.where(cb.equal(from.get("business"), business),
                    cb.isNull(from.get("close")),
                    cb.lessThan(from.<Date>get("dateTransaction"), dateClose));

            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
        }
        return new ArrayList();
    }

    private List<BusinessSell> getPendingBusinessSell(Business business) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessSell> cq = cb.createQuery(BusinessSell.class);
            Root<BusinessSell> from = cq.from(BusinessSell.class);
            cq.select(from);
            cq.where(cb.equal(from.get("business"), business),
                    cb.isNull(from.get("businessClose")));

            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
        }
        return new ArrayList();
    }
    
    private List<BusinessSell> getPendingBusinessSell(Business business, Date dateClose) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessSell> cq = cb.createQuery(BusinessSell.class);
            Root<BusinessSell> from = cq.from(BusinessSell.class);
            cq.select(from);
            cq.where(cb.equal(from.get("business"), business),
                    cb.isNull(from.get("businessClose")),
                    cb.lessThan(from.<Date>get("dateSell"), dateClose));

            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
        }
        return new ArrayList();
    }

    public List<BusinessTransaction> getBusinessCloseTransactions(BusinessClose businessClose) throws EmptyListException, GeneralException {
        List<BusinessTransaction> businessTransactions = new ArrayList();
        try {
            List<BusinessBalanceIncoming> incomings = getBusinessCloseIncomingTransactions(businessClose);
            for (BusinessBalanceIncoming incoming : incomings) {
                businessTransactions.add(new BusinessTransaction(incoming));
            }
        } catch (GeneralException e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }

        try {
            List<BusinessBalanceOutgoing> outgoings = getBusinessCloseOutgoingTransactions(businessClose);
            for (BusinessBalanceOutgoing outgoing : outgoings) {
                businessTransactions.add(new BusinessTransaction(outgoing));
            }
        } catch (GeneralException e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (businessTransactions.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }

        return businessTransactions;
    }

    private List<BusinessBalanceIncoming> getBusinessCloseIncomingTransactions(BusinessClose businessClose) throws GeneralException {
        List<BusinessBalanceIncoming> incomings = new ArrayList();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceIncoming> cq = cb.createQuery(BusinessBalanceIncoming.class);
            Root<BusinessBalanceIncoming> from = cq.from(BusinessBalanceIncoming.class);
            cq.select(from);

            List<Predicate> predList = new ArrayList();

            predList.add(cb.equal(from.get("close"), businessClose));
            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            Query query = entityManager.createQuery(cq);

            incomings = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        return incomings;
    }

    private List<BusinessBalanceOutgoing> getBusinessCloseOutgoingTransactions(BusinessClose businessClose) throws GeneralException {
        List<BusinessBalanceOutgoing> incomings = new ArrayList();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceOutgoing> cq = cb.createQuery(BusinessBalanceOutgoing.class);
            Root<BusinessBalanceOutgoing> from = cq.from(BusinessBalanceOutgoing.class);
            cq.select(from);

            List<Predicate> predList = new ArrayList();

            predList.add(cb.equal(from.get("close"), businessClose));
            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            Query query = entityManager.createQuery(cq);

            incomings = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        return incomings;
    }

    public List<BusinessSell> getBusinessCloseSells(BusinessClose businessClose) throws EmptyListException, GeneralException {
        List<BusinessSell> sells = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceIncoming> cq = cb.createQuery(BusinessBalanceIncoming.class);
            Root<BusinessBalanceIncoming> from = cq.from(BusinessBalanceIncoming.class);
            cq.select(from);
            cq.where(cb.equal(from.get("businessClose"), businessClose));
            Query query = entityManager.createQuery(cq);
            sells = query.getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (sells == null || sells.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return sells;
    }

    public List<BusinessClose> getBusinessCloseReport(Business business, Date startDate, Date endDate) throws EmptyListException, GeneralException {
        List<BusinessClose> businessCloseReport = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessClose> cq = cb.createQuery(BusinessClose.class);
            Root<BusinessClose> from = cq.from(BusinessClose.class);
            cq.select(from);

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateClose");

            predList.add(cb.equal(from.get("business"), business));
            predList.add(cb.between(dateEntryPath, startDate, endDate));
            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            Query query = entityManager.createQuery(cq);

            businessCloseReport = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (businessCloseReport == null || businessCloseReport.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return businessCloseReport;

    }

}
