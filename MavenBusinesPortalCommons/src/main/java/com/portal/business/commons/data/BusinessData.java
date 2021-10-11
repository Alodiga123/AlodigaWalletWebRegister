package com.portal.business.commons.data;

import com.alodiga.wallet.common.ejb.PreferencesEJB;
import com.alodiga.wallet.common.genericEJB.EJBRequest;
import com.portal.business.commons.enumeration.OperationType;
import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.exceptions.RegisterNotFoundException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.generic.WsRequest;
import com.portal.business.commons.models.Business;
import com.portal.business.commons.models.BusinessBalanceIncoming;
import com.portal.business.commons.models.BusinessBalanceOutgoing;
import com.portal.business.commons.models.BusinessClose;
import com.portal.business.commons.models.BusinessTransaction;
import com.portal.business.commons.models.Operator;
import com.portal.business.commons.utils.EjbConstants;
import com.alodiga.wallet.common.manager.PreferenceManager;
import com.alodiga.wallet.common.model.PreferenceFieldEnum;
import com.alodiga.wallet.common.utils.EJBServiceLocator;
import com.alodiga.wallet.common.utils.EjbUtils;
import com.alodiga.wallet.common.utils.QueryConstants;
import com.portal.business.commons.models.BusinessStatus;
import com.portal.business.commons.models.LimitedsTransactionsResponse;
import com.portal.business.commons.models.ResponseCode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

/**
 *
 * @author hvarona
 */
public class BusinessData extends AbstractBusinessPortalWs {

    private static final Logger LOG = Logger.getLogger(BusinessData.class);

    public Business getBusinessById(Long id) throws NullParameterException, GeneralException {
        if (id == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "id"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Business> cq = cb.createQuery(Business.class);
            Root<Business> from = cq.from(Business.class);
            cq.select(from).where(cb.equal(from.get("id"), id));
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public Business getBusinessByCode(String code) throws NullParameterException, GeneralException {
        if (code == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "code"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Business> cq = cb.createQuery(Business.class);
            Root<Business> from = cq.from(Business.class);
            cq.select(from).where(cb.equal(from.get("code"), code));
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public Business getBusinessByIdentification(String identification) throws NullParameterException, GeneralException {
        if (identification == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "indetification"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Business> cq = cb.createQuery(Business.class);
            Root<Business> from = cq.from(Business.class);
            cq.select(from).where(cb.equal(from.get("indetification"), identification));
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public Business getBusinessByEmail(String email) throws NullParameterException, GeneralException {
        if (email == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "email"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Business> cq = cb.createQuery(Business.class);
            Root<Business> from = cq.from(Business.class);
            cq.select(from).where(cb.equal(from.get("email"), email));
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public Business getBusinessByPhone(String phoneNumber) throws NullParameterException, GeneralException {
        if (phoneNumber == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "phoneNumber"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Business> cq = cb.createQuery(Business.class);
            Root<Business> from = cq.from(Business.class);
            cq.select(from).where(cb.equal(from.get("phoneNumber"), phoneNumber));
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public Business getBusinessByLogin(String login) throws NullParameterException, GeneralException {
        if (login == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "code"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Business> cq = cb.createQuery(Business.class);
            Root<Business> from = cq.from(Business.class);
            cq.select(from).where(cb.equal(from.get("login"), login));
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public Business getBusinessByAffiliation(Long affiliationId) throws NullParameterException, GeneralException {
        if (affiliationId == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "affiliationId"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Business> cq = cb.createQuery(Business.class);
            Root<Business> from = cq.from(Business.class);
            cq.select(from).where(cb.equal(from.get("idPerson"), affiliationId));
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<Business> getBusinessList() throws EmptyListException, GeneralException {

        List<Business> businessList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Business> cq = cb.createQuery(Business.class);
            Root<Business> from = cq.from(Business.class);
            businessList = entityManager.createQuery(cq.select(from)).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (businessList == null || businessList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return businessList;
    }

    public Business loadBusiness(WsRequest request) throws RegisterNotFoundException, NullParameterException, GeneralException {
        return (Business) loadEntity(Operator.class, request, LOG, getMethodName());
    }

    public Business saveBusiness(Business business) throws NullParameterException, GeneralException {
        if (business == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "business"), null);
        }
        return (Business) saveEntity(business);
    }

    public List<BusinessStatus> getBusinessStatus() throws EmptyListException, GeneralException {

        List<BusinessStatus> businessStatusList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessStatus> cq = cb.createQuery(BusinessStatus.class);
            Root<BusinessStatus> from = cq.from(BusinessStatus.class);
            businessStatusList = entityManager.createQuery(cq.select(from)).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (businessStatusList == null || businessStatusList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return businessStatusList;
    }

    public BusinessStatus getBusinessStatusByName(String name) throws NullParameterException, GeneralException {
        if (name == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "code"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessStatus> cq = cb.createQuery(BusinessStatus.class);
            Root<BusinessStatus> from = cq.from(BusinessStatus.class);
            cq.select(from).where(cb.equal(from.get("name"), name));
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<BusinessClose> getBusinessClose(Business business) throws EmptyListException, GeneralException {
        List<BusinessClose> businessCloseList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessClose> cq = cb.createQuery(BusinessClose.class);
            Root<BusinessClose> from = cq.from(BusinessClose.class);
            cq.select(from);
            cq.where(cb.equal(from.get("business"), business));
            businessCloseList = entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (businessCloseList == null || businessCloseList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return businessCloseList;
    }

    public synchronized BusinessBalanceIncoming saveIncomingBalance(BusinessBalanceIncoming incoming) throws NullParameterException, GeneralException {
        if (incoming == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "incoming"), null);
        }
        return (BusinessBalanceIncoming) saveEntity(incoming);
    }

    public synchronized BusinessBalanceOutgoing saveOutgoinBalance(BusinessBalanceOutgoing outgoing) throws NullParameterException, GeneralException {
        if (outgoing == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "outgoing"), null);
        }
        return (BusinessBalanceOutgoing) saveEntity(outgoing);
    }

    private List<BusinessBalanceIncoming> getBusinessIncomingTransactions(Business business, Date startDate, Date endDate, OperationType type) throws GeneralException {
        List<BusinessBalanceIncoming> incomings = new ArrayList();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceIncoming> cq = cb.createQuery(BusinessBalanceIncoming.class);
            Root<BusinessBalanceIncoming> from = cq.from(BusinessBalanceIncoming.class);
            cq.select(from);

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateTransaction");

            predList.add(cb.equal(from.get("business"), business));
            predList.add(cb.between(dateEntryPath, startDate, endDate));
            if (type != null) {
                predList.add(cb.equal(from.get("type"), type));
            }
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

    private List<BusinessBalanceOutgoing> getBusinessOutgoingTransactions(Business business, Date startDate, Date endDate, OperationType type) throws GeneralException {
        List<BusinessBalanceOutgoing> outgoings = new ArrayList();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceOutgoing> cq = cb.createQuery(BusinessBalanceOutgoing.class);
            Root<BusinessBalanceOutgoing> from = cq.from(BusinessBalanceOutgoing.class);
            cq.select(from);
            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateTransaction");

            predList.add(cb.equal(from.get("business"), business));
            predList.add(cb.between(dateEntryPath, startDate, endDate));
            if (type != null) {
                predList.add(cb.equal(from.get("type"), type));
            }
            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            outgoings = entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        return outgoings;
    }

    public List<BusinessTransaction> getBusinessTransactions(Business business, Date startDate, Date endDate) throws EmptyListException, GeneralException {
        return getBusinessTransactions(business, startDate, endDate, null);
    }

    public List<BusinessTransaction> getBusinessTransactions(Business business, Date startDate, Date endDate, OperationType type) throws EmptyListException, GeneralException {
        List<BusinessTransaction> businessTransactions = new ArrayList();
        try {
            System.out.println("Generando reporte desde " + startDate + " hasta " + endDate + " de operacion " + type);
            List<BusinessBalanceIncoming> incomings = getBusinessIncomingTransactions(business, startDate, endDate, type);
            for (BusinessBalanceIncoming incoming : incomings) {
                businessTransactions.add(new BusinessTransaction(incoming));
            }
        } catch (GeneralException e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }

        try {
            List<BusinessBalanceOutgoing> outgoings = getBusinessOutgoingTransactions(business, startDate, endDate, type);
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

    public BusinessTransaction getBusinessTranasction(Business business, String id) throws GeneralException {
        try {
            BusinessBalanceIncoming incoming = getBusinessIncomingTransaction(business, id);
            if (incoming != null) {
                return new BusinessTransaction(incoming);
            }
        } catch (GeneralException e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }

        try {
            BusinessBalanceOutgoing outgoings = getBusinessOutgoingTransaction(business, id);
            if (outgoings != null) {
                return new BusinessTransaction(outgoings);
            }
        } catch (GeneralException e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        return null;
    }

    private BusinessBalanceIncoming getBusinessIncomingTransaction(Business business, String id) throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceIncoming> cq = cb.createQuery(BusinessBalanceIncoming.class);
            Root<BusinessBalanceIncoming> from = cq.from(BusinessBalanceIncoming.class);
            cq.select(from);
            cq.where(cb.equal(from.get("business"), business),
                    cb.equal(from.get("id"), id));
            return (BusinessBalanceIncoming) entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    private BusinessBalanceOutgoing getBusinessOutgoingTransaction(Business business, String id) throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BusinessBalanceOutgoing> cq = cb.createQuery(BusinessBalanceOutgoing.class);
            Root<BusinessBalanceOutgoing> from = cq.from(BusinessBalanceOutgoing.class);
            cq.select(from);
            cq.where(cb.equal(from.get("business"), business),
                    cb.equal(from.get("id"), id));
            return (BusinessBalanceOutgoing) entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    private Long getBusinessIncomingTransactionsNumber(Business business, Date startDate, Date endDate, OperationType type) throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<BusinessBalanceIncoming> from = cq.from(BusinessBalanceIncoming.class);
            cq.select(cb.count(from));

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateTransaction");

            predList.add(cb.equal(from.get("business"), business));
            predList.add(cb.between(dateEntryPath, startDate, endDate));
            if (type != null) {
                predList.add(cb.equal(from.get("type"), type));
            }
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

    private Long getBusinessOutgoingTransactionsNumber(Business business, Date startDate, Date endDate, OperationType type) throws GeneralException {
        List<BusinessBalanceOutgoing> outgoings = new ArrayList();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<BusinessBalanceOutgoing> from = cq.from(BusinessBalanceOutgoing.class);
            cq.select(cb.count(from));

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateTransaction");

            predList.add(cb.equal(from.get("business"), business));
            predList.add(cb.between(dateEntryPath, startDate, endDate));
            if (type != null) {
                predList.add(cb.equal(from.get("type"), type));
            }
            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            return (Long) entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public long getBusinessTransactionsNumber(Business business, Date startDate, Date endDate, OperationType type) throws GeneralException {
        long answer = 0;
        try {
            System.out.println("Generando reporte desde " + startDate + " hasta " + endDate + " de operacion " + type);
            answer += getBusinessIncomingTransactionsNumber(business, startDate, endDate, type);

        } catch (GeneralException e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            answer += getBusinessOutgoingTransactionsNumber(business, startDate, endDate, type);
        } catch (GeneralException e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return answer;
    }

    public Float getAmountBusinessIncomingTransactions(Business business, Date startDate, Date endDate, OperationType type) throws GeneralException {
        Float transactionAmount = null;
        Double transactionResult = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Double> cq = cb.createQuery(Double.class);
            Root<BusinessBalanceIncoming> from = cq.from(BusinessBalanceIncoming.class);
            cq.select(cb.sumAsDouble(from.<Float>get("totalAmount")));

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateTransaction");

            predList.add(cb.equal(from.get("business"), business));

            predList.add(cb.between(dateEntryPath, startDate, endDate));
            if (type != null) {
                predList.add(cb.equal(from.get("type"), type));
            }
            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            Query query = entityManager.createQuery(cq);

            transactionResult = (Double) query.getSingleResult();
            try {
                transactionResult = transactionResult != null ? transactionResult : 0f;
            } catch (NullPointerException e) {
                //No devuelve nada
                transactionResult = 0d;
            }
            transactionAmount = transactionResult.floatValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        return transactionAmount;
    }

    public Long getQuantityBusinessIncomingTransactions(Business business, Date startDate, Date endDate, OperationType type) throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<BusinessBalanceIncoming> from = cq.from(BusinessBalanceIncoming.class);
            cq.select(cb.count(from));

            List<Predicate> predList = new ArrayList();
            Path<Date> dateEntryPath = from.get("dateTransaction");

            predList.add(cb.equal(from.get("business"), business));

            predList.add(cb.between(dateEntryPath, startDate, endDate));
            if (type != null) {
                predList.add(cb.equal(from.get("type"), type));
            }
            Predicate[] predArray = new Predicate[predList.size()];
            predList.toArray(predArray);

            cq.where(predArray);
            return (Long) entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<Float> getEntireSalesAmountByBusiness(Business bussiness, OperationType type) throws NullParameterException, GeneralException {
        List<Float> sales = new ArrayList();
        sales.add(getAmountBusinessIncomingTransactions(bussiness, EjbUtils.getBeginningDate(new Date()), EjbUtils.getEndingDate(new Date()), type));
        sales.add(getAmountBusinessIncomingTransactions(bussiness, EjbUtils.getBeginningDateMonth(new Date()), new Date(), type));
        sales.add(getAmountBusinessIncomingTransactions(bussiness, EjbUtils.getBeginningDateAnnual(new Date()), new Date(), type));
        return sales;
    }

    public List<Integer> getEntireSalesQuantityByBusiness(Business bussiness, OperationType type) throws NullParameterException, GeneralException {
        List<Integer> salesQuantity = new ArrayList();
        salesQuantity.add((getQuantityBusinessIncomingTransactions(bussiness, EjbUtils.getBeginningDate(new Date()), EjbUtils.getEndingDate(new Date()), type)).intValue());
        salesQuantity.add((getQuantityBusinessIncomingTransactions(bussiness, EjbUtils.getBeginningDateMonth(new Date()), new Date(), type)).intValue());
        salesQuantity.add((getQuantityBusinessIncomingTransactions(bussiness, EjbUtils.getBeginningDateAnnual(new Date()), new Date(), type)).intValue());
        return salesQuantity;
    }

    public Map<com.portal.business.commons.models.PreferenceFieldEnum, String> getTransactionalLimitByBusiness(Business business) {
        PreferencesEJB preferenceEJB = (PreferencesEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.PREFERENCES_EJB);
        EJBRequest request = new EJBRequest();
        Map<String, Object> params = new HashMap();
        params.put(QueryConstants.PARAM_BUSSINESS_ID, business.getId());
        params.put(QueryConstants.CLASSIFICATION_ID, 2L);
        params.put(QueryConstants.PARAM_PRODUCT_ID, 1L);
        params.put(QueryConstants.PARAM_TRANSACTION_TYPE_ID, 1L);
        request.setParams(params);

        try {
            Map<Long, String> preferencesBussiness = preferenceEJB.getLastPreferenceValuesByBusiness(request);
            Map<Long, String> preferencesGlobal = PreferenceManager.getInstance().getPreferences();
            Map<com.portal.business.commons.models.PreferenceFieldEnum, String> answer = new HashMap();
            for (com.portal.business.commons.models.PreferenceFieldEnum fieldEnum : com.portal.business.commons.models.PreferenceFieldEnum.values()) {
                if (preferencesBussiness.get(fieldEnum.getId()) != null) {
                    answer.put(fieldEnum, preferencesBussiness.get(fieldEnum.getId()));
                } else {
                    if (preferencesGlobal.get(fieldEnum.getId()) != null) {
                        answer.put(fieldEnum, preferencesGlobal.get(fieldEnum.getId()));
                    } else {
                        answer.put(fieldEnum, "N/A");
                    }
                }
            }

            return answer;

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BusinessData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public LimitedsTransactionsResponse validTransacionalLimit(Business business, OperationType type) throws NullParameterException, GeneralException {
        LimitedsTransactionsResponse response = new LimitedsTransactionsResponse();
        List<Integer> salesQuantity = getEntireSalesQuantityByBusiness(business, type);
        if (type.equals(OperationType.CARD_ACTIVATED)) {
            List<Integer> salesQuantity2 = getEntireSalesQuantityByBusiness(business, OperationType.CARD_DEACTIVATED);
            List<Integer> salesQuantityTotal = new ArrayList();
            for (int i = 0; i < salesQuantity.size(); i++) {
                salesQuantityTotal.add(salesQuantity.get(i) + salesQuantity2.get(i));
            }
            salesQuantity = salesQuantityTotal;
        }
        PreferencesEJB preferenceEJB = (PreferencesEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.PREFERENCES_EJB);
        EJBRequest request = new EJBRequest();
        Map<String, Object> params = new HashMap();
        params.put(QueryConstants.PARAM_BUSSINESS_ID, business.getId());
        params.put(QueryConstants.CLASSIFICATION_ID, 2L);
        params.put(QueryConstants.PARAM_PRODUCT_ID, 1L);
        params.put(QueryConstants.PARAM_TRANSACTION_TYPE_ID, 1L);
        request.setParams(params);
        Map<Long, String> preferencesBussiness;
        Map<Long, String> preferencesGlobal;
        Integer disabledTransactionBussiness = 1;
        Integer numberCardActivate = 0;
        try {
            preferencesBussiness = preferenceEJB.getLastPreferenceValuesByBusiness(request);
            PreferenceManager pManager = PreferenceManager.getInstance();
            preferencesGlobal = pManager.getPreferences();
            //se debe consultar si las transacciones estan habilitadas en las preferencias especificas y en las generales
            disabledTransactionBussiness = preferencesBussiness.get(PreferenceFieldEnum.DISABLED_TRANSACTION_ID.getId()) != null
                    ? Integer.parseInt(preferencesBussiness.get(PreferenceFieldEnum.DISABLED_TRANSACTION_ID.getId())) : 1;
            Integer disabledTransaction = Integer.parseInt(preferencesGlobal.get(PreferenceFieldEnum.DISABLED_TRANSACTION_ID.getId()));
            //si el tipo de operacion es activar tarjetas solo evaluamos esa preferencia
            if (type == OperationType.CARD_ACTIVATED) {
                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_NUMBER_OF_CARDS_ENABLED.getId()) != null) {
                    numberCardActivate = Integer.parseInt(preferencesBussiness.get(PreferenceFieldEnum.MAX_NUMBER_OF_CARDS_ENABLED.getId()));
                } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_NUMBER_OF_CARDS_ENABLED.getId()) != null) {
                    numberCardActivate = Integer.parseInt(preferencesGlobal.get(PreferenceFieldEnum.MAX_NUMBER_OF_CARDS_ENABLED.getId()));
                } else {
                    numberCardActivate = Integer.MAX_VALUE;
                }
                if (salesQuantity.get(0) > numberCardActivate) {
                    response.setCode(ResponseCode.MAX_NUMBER_OF_CARDS_ENABLED.getCodigo());
                    response.setMessage(ResponseCode.MAX_NUMBER_OF_CARDS_ENABLED.getMessage());
                    response.setValid(false);
                    return response;
                }
                //si el tipo de operacion no es activar tarjetas evaluamos los limites transaccionales   
            } else {
                List<Float> sales = getEntireSalesAmountByBusiness(business, type);
                Float maxAmountDaily;
                Float maxAmountMonth;
                Float maxAmountYearly;
                Integer maxTransactionQuantityDaily = 0;
                Integer maxTransactionQuantityMonth = 0;
                Integer maxTransactionQuantityYaerly = 0;
                //preguntar primero si existe la preferencia especifica para el negocio sino tomar la general
                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()) != null) {
                    maxAmountDaily = Float.parseFloat(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()));
                } else {
                    maxAmountDaily = Float.parseFloat(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()));
                }
                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()) != null) {
                    maxAmountMonth = Float.parseFloat(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()));
                } else {
                    maxAmountMonth = Float.parseFloat(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()));
                }
                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()) != null) {
                    maxAmountYearly = Float.parseFloat(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()));
                } else {
                    maxAmountYearly = Float.parseFloat(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()));
                }
                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getId()) != null) {
                    maxTransactionQuantityDaily = Integer.parseInt(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getId()));
                } else {
                    maxTransactionQuantityDaily = Integer.parseInt(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getId()));
                }
                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getId()) != null) {
                    maxTransactionQuantityMonth = Integer.parseInt(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getId()));
                } else {
                    maxTransactionQuantityMonth = Integer.parseInt(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getId()));
                }
                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getId()) != null) {
                    maxTransactionQuantityYaerly = Integer.parseInt(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getId()));
                } else {
                    maxTransactionQuantityYaerly = Integer.parseInt(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getId()));
                }

                System.out.println("maxAmountDaily : " + maxAmountDaily
                        + " maxAmountDaily : " + maxAmountDaily
                        + " maxAmountMonth : " + maxAmountMonth
                        + " maxAmountYearly : " + maxAmountYearly
                        + " maxTransactionQuantityDaily : " + maxTransactionQuantityDaily
                        + " maxTransactionQuantityMonth : " + maxTransactionQuantityMonth
                        + " maxTransactionQuantityYaerly : " + maxTransactionQuantityYaerly);
                if (disabledTransactionBussiness != 1 && disabledTransaction != 1) {
                    response.setCode(ResponseCode.TRANSACTION_DISABLED.getCodigo());
                    response.setMessage(ResponseCode.TRANSACTION_DISABLED.getMessage());
                    response.setValid(false);
                    return response;
                }
                if (sales.get(0) > maxAmountDaily) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_AMOUNT_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_AMOUNT_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }
                if (sales.get(1) > maxAmountMonth) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }
                if (sales.get(2) > maxAmountYearly) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }
                if (salesQuantity.get(0) > maxTransactionQuantityDaily) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }
                if (salesQuantity.get(1) > maxTransactionQuantityMonth) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }
                if (salesQuantity.get(2) > maxTransactionQuantityYaerly) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }
            }
            response.setCode(ResponseCode.SUCESS.getCodigo());
            response.setMessage(ResponseCode.SUCESS.getMessage());
            response.setValid(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        return response;
    }

}
