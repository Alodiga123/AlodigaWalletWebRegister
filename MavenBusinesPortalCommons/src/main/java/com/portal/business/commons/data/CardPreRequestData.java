package com.portal.business.commons.data;

import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.exceptions.RegisterNotFoundException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.models.CardPreRequest;
import com.portal.business.commons.utils.EjbConstants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
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
public class CardPreRequestData extends AbstractBusinessPortalWs {

    private static final Logger LOG = Logger.getLogger(CardPreRequestData.class);

    public CardPreRequest saveorUpdateCardPreReques(CardPreRequest cardPreRequest) throws NullParameterException, GeneralException {
        EntityTransaction transaction = entityManager.getTransaction();
        try {

            if (!transaction.isActive()) {
                transaction.begin();
            }
            if (cardPreRequest.getId() != null) {
                entityManagerWrapper.update(cardPreRequest);
            } else {
                entityManagerWrapper.save(cardPreRequest);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (Exception e1) {
                throw new GeneralException("GeneralException saveEntity");
            }
            throw new GeneralException("GeneralException saveEntity");
        }
        return cardPreRequest;
    }

    public CardPreRequest getRequestById(Long requestId) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (requestId == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "requestId"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CardPreRequest> cq = cb.createQuery(CardPreRequest.class);
            Root<CardPreRequest> from = cq.from(CardPreRequest.class);

            cq.select(from).where(cb.equal(from.get("id"), requestId));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (CardPreRequest) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("No se encontro la solicitud");
        } catch (Exception ex) {
            ex.getMessage();
            throw new GeneralException("No se encontro la solicitud");
        }
    }

    public List<CardPreRequest> getAllRequest() throws EmptyListException, GeneralException {

        List<CardPreRequest> requestList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CardPreRequest> cq = cb.createQuery(CardPreRequest.class);
            Root<CardPreRequest> from = cq.from(CardPreRequest.class);
            requestList = entityManager.createQuery(cq.select(from)).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (requestList == null || requestList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return requestList;

    }

    public List<CardPreRequest> getRequestsReport(Date startDate, Date endDate, List<CardPreRequest.CardPreRequestStatus> status) throws EmptyListException, GeneralException {

        List<CardPreRequest> requestList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CardPreRequest> cq = cb.createQuery(CardPreRequest.class);
            Root<CardPreRequest> from = cq.from(CardPreRequest.class);
            cq.select(from);

            List<Predicate> predList = new ArrayList();
            if (startDate != null) {
                if (endDate == null) {
                    endDate = new Date();
                }
                Path<Date> dateEntryPath = from.get("dateRequest");

                predList.add(cb.between(dateEntryPath, startDate, endDate));
            }

            if (status != null && !status.isEmpty()) {
                List<Predicate> predStatusList = new ArrayList();
                for (CardPreRequest.CardPreRequestStatus stat : status) {
                    predStatusList.add(cb.equal(from.get("status"), stat));
                }
                predList.add(cb.or(predStatusList.toArray(new Predicate[predStatusList.size()])));
            }

            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            Query query = entityManager.createQuery(cq);

            requestList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (requestList == null || requestList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return requestList;
    }

}
