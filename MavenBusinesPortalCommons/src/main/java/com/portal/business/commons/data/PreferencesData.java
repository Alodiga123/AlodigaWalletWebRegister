package com.portal.business.commons.data;

import com.alodiga.wallet.common.ejb.BusinessPortalEJB;
import com.alodiga.wallet.common.ejb.PreferencesEJB;
import com.alodiga.wallet.common.ejb.UtilsEJB;
import com.alodiga.wallet.common.exception.EmptyListException;
import com.alodiga.wallet.common.exception.RegisterNotFoundException;
import com.alodiga.wallet.common.genericEJB.EJBRequest;
import com.alodiga.wallet.common.manager.PreferenceManager;
import com.alodiga.wallet.common.model.PreferenceField;
import com.alodiga.wallet.common.model.PreferenceFieldEnum;
import com.alodiga.wallet.common.model.PreferenceValue;
import com.alodiga.wallet.common.model.TransactionType;
import com.alodiga.wallet.common.utils.EJBServiceLocator;
import com.alodiga.wallet.common.utils.EjbUtils;
import com.alodiga.wallet.common.utils.QueryConstants;
import com.portal.business.commons.enumeration.BusinessServiceType;
import com.portal.business.commons.enumeration.OperationType;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.models.Business;
import com.portal.business.commons.models.BusinessBalanceIncoming;
import com.portal.business.commons.models.BusinessBalanceOutgoing;
import com.portal.business.commons.models.LimitedsTransactionsResponse;
import com.portal.business.commons.models.ResponseCode;
import com.portal.business.commons.models.TransactionCommissionResponse;
import com.portal.business.commons.utils.EjbConstants;
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
 * @author henry
 */
public class PreferencesData extends AbstractBusinessPortalWs {

    private static final Logger LOG = Logger.getLogger(PreferencesData.class);

    public Map<com.portal.business.commons.models.PreferenceFieldEnum, String> getPreferencesByBusiness(Business business, BusinessServiceType serviceType, Long productId) {
        TransactionType transactionType = null;
        try {
            UtilsEJB utilsEJB = (UtilsEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.UTILS_EJB);
            //TODO cambiar esto para que sea una funcion directamente
            List<TransactionType> transactionTypes = utilsEJB.getTransactionType(new EJBRequest());
            for (TransactionType type : transactionTypes) {
                if (type.getCode().equals(serviceType.getPreferenceName())) {
                    transactionType = type;
                    break;
                }
            }

        } catch (EmptyListException | com.alodiga.wallet.common.exception.GeneralException | com.alodiga.wallet.common.exception.NullParameterException ex) {
            java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        if (transactionType == null) {
            //No existe limite se asume que no hay limite
            return null;
        }

        PreferencesEJB preferenceEJB = (PreferencesEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.PREFERENCES_EJB);
        EJBRequest request = new EJBRequest();
        Map<String, Object> params = new HashMap();
        params.put(QueryConstants.PARAM_BUSSINESS_ID, business.getId());
        params.put(QueryConstants.CLASSIFICATION_ID, 2L);
        params.put(QueryConstants.PARAM_PRODUCT_ID, productId);
        params.put(QueryConstants.PARAM_TRANSACTION_TYPE_ID, transactionType.getId());
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

    public LimitedsTransactionsResponse validateService(Business business, BusinessServiceType serviceType, long productId) {

        LimitedsTransactionsResponse response = new LimitedsTransactionsResponse();
        TransactionType transactionType = null;
        try {
            UtilsEJB utilsEJB = (UtilsEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.UTILS_EJB);
            //TODO cambiar esto para que sea una funcion directamente
            List<TransactionType> transactionTypes = utilsEJB.getTransactionType(new EJBRequest());
            for (TransactionType type : transactionTypes) {
                if (type.getCode().equals(serviceType.getPreferenceName())) {
                    transactionType = type;
                    break;
                }
            }

        } catch (EmptyListException | com.alodiga.wallet.common.exception.GeneralException | com.alodiga.wallet.common.exception.NullParameterException ex) {
            java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
            response.setCode(ResponseCode.INTERNAL_ERROR.getCodigo());
            response.setMessage(ResponseCode.INTERNAL_ERROR.getMessage());
            response.setValid(false);
            return response;
        }

        if (transactionType == null) {
            //No existe limite se asume que no hay limite
            response.setCode(ResponseCode.SUCESS.getCodigo());
            response.setMessage(ResponseCode.SUCESS.getMessage());
            response.setValid(true);
            return response;
        }

        PreferencesEJB preferenceEJB = (PreferencesEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.PREFERENCES_EJB);
        EJBRequest request = new EJBRequest();
        Map<String, Object> params = new HashMap();
        params.put(QueryConstants.PARAM_BUSSINESS_ID, business.getId());
        params.put(QueryConstants.CLASSIFICATION_ID, 2L);
        params.put(QueryConstants.PARAM_PRODUCT_ID, productId);
        params.put(QueryConstants.PARAM_TRANSACTION_TYPE_ID, transactionType.getId());
        request.setParams(params);

        try {
            Map<Long, String> preferencesBussiness = preferenceEJB.getLastPreferenceValuesByBusiness(request);
            PreferenceManager pManager = PreferenceManager.getInstance();
            Map<Long, String> preferencesGlobal = pManager.getPreferences();

            List<Integer> salesQuantity = getEntireSalesQuantityByBusiness(business, serviceType.getOperationType());
            if (serviceType.equals(BusinessServiceType.CARD_ACTIVATION)) {
                List<Integer> salesQuantity2 = getEntireSalesQuantityByBusiness(business, OperationType.CARD_DEACTIVATED);
                List<Integer> salesQuantityTotal = new ArrayList();
                for (int i = 0; i < salesQuantity.size(); i++) {
                    salesQuantityTotal.add(salesQuantity.get(i) + salesQuantity2.get(i));
                }
                salesQuantity = salesQuantityTotal;
            }

            Integer maxTransactionQuantityDaily = 0;

            if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getId()) != null) {
                maxTransactionQuantityDaily = Integer.parseInt(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getId()));
            } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getId()) != null) {
                maxTransactionQuantityDaily = Integer.parseInt(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getId()));
            } else {
                //No existe, asi que se asume que es ilimitado
                maxTransactionQuantityDaily = -1;
            }
            if (maxTransactionQuantityDaily != -1 && salesQuantity.get(0) > maxTransactionQuantityDaily) {
                response.setCode(ResponseCode.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getCodigo());
                response.setMessage(ResponseCode.MAX_TRANSACTION_QUANTITY_DAILY_LIMIT_ID.getMessage());
                response.setValid(false);
                return response;
            }

            Integer maxTransactionQuantityMonth = 0;
            if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getId()) != null) {
                maxTransactionQuantityMonth = Integer.parseInt(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getId()));
            } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getId()) != null) {
                maxTransactionQuantityMonth = Integer.parseInt(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getId()));
            } else {
                maxTransactionQuantityMonth = -1;
            }

            if (maxTransactionQuantityMonth != -1 && salesQuantity.get(1) > maxTransactionQuantityMonth) {
                response.setCode(ResponseCode.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getCodigo());
                response.setMessage(ResponseCode.MAX_TRANSACTION_QUANTITY_MONTH_LIMIT_ID.getMessage());
                response.setValid(false);
                return response;
            }

            Integer maxTransactionQuantityYaerly = 0;
            if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getId()) != null) {
                maxTransactionQuantityYaerly = Integer.parseInt(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getId()));
            } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getId()) != null) {
                maxTransactionQuantityYaerly = Integer.parseInt(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getId()));
            } else {
                maxTransactionQuantityYaerly = -1;
            }

            if (maxTransactionQuantityYaerly != -1 && salesQuantity.get(2) > maxTransactionQuantityYaerly) {
                response.setCode(ResponseCode.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getCodigo());
                response.setMessage(ResponseCode.MAX_TRANSACTION_QUANTITY_YEAR_LIMIT_ID.getMessage());
                response.setValid(false);
                return response;
            }

            if (serviceType.isHasAmount()) {
                List<Float> sales = getEntireSalesAmountByBusiness(business, serviceType.getOperationType());
                Float maxAmountDaily;
                Float maxAmountMonth;
                Float maxAmountYearly;

                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()) != null) {
                    maxAmountDaily = Float.parseFloat(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()));
                } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()) != null) {
                    maxAmountDaily = Float.parseFloat(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()));
                } else {
                    maxAmountDaily = -1F;
                }
                if (maxAmountDaily != -1 && sales.get(0) > maxAmountDaily) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_AMOUNT_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_AMOUNT_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }
                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()) != null) {
                    maxAmountMonth = Float.parseFloat(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()));
                } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()) != null) {
                    maxAmountMonth = Float.parseFloat(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()));
                } else {
                    maxAmountMonth = -1F;
                }
                if (maxAmountMonth != -1 && sales.get(1) > maxAmountMonth) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }

                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()) != null) {
                    maxAmountYearly = Float.parseFloat(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()));
                } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()) != null) {
                    maxAmountYearly = Float.parseFloat(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()));
                } else {
                    maxAmountYearly = -1F;
                }

                if (maxAmountYearly != -1 && sales.get(2) > maxAmountYearly) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }

            }
            response.setCode(ResponseCode.SUCESS.getCodigo());
            response.setMessage(ResponseCode.SUCESS.getMessage());
            response.setValid(true);

        } catch (com.alodiga.wallet.common.exception.GeneralException ex) {
            java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
            response.setCode(ResponseCode.INTERNAL_ERROR.getCodigo());
            response.setMessage(ResponseCode.INTERNAL_ERROR.getMessage());
            response.setValid(false);
        } catch (RegisterNotFoundException | com.alodiga.wallet.common.exception.NullParameterException | EmptyListException ex) {
            java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
            response.setCode(ResponseCode.INTERNAL_ERROR.getCodigo());
            response.setMessage(ResponseCode.INTERNAL_ERROR.getMessage());
            response.setValid(false);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
            response.setCode(ResponseCode.INTERNAL_ERROR.getCodigo());
            response.setMessage(ResponseCode.INTERNAL_ERROR.getMessage());
            response.setValid(false);
        }
        return response;

    }

    public LimitedsTransactionsResponse validateServiceAmount(Business business, BusinessServiceType serviceType, long productId, float amount) {
        LimitedsTransactionsResponse response = new LimitedsTransactionsResponse();
        if (!serviceType.isHasAmount()) {
            response.setCode(ResponseCode.INTERNAL_ERROR.getCodigo());
            response.setMessage(ResponseCode.INTERNAL_ERROR.getMessage());
            response.setValid(false);
        } else {

            TransactionType transactionType = null;
            try {
                UtilsEJB utilsEJB = (UtilsEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.UTILS_EJB);
                //TODO cambiar esto para que sea una funcion directamente
                List<TransactionType> transactionTypes = utilsEJB.getTransactionType(new EJBRequest());
                for (TransactionType type : transactionTypes) {
                    if (type.getCode().equals(serviceType.getPreferenceName())) {
                        transactionType = type;
                        break;
                    }
                }

            } catch (EmptyListException | com.alodiga.wallet.common.exception.GeneralException | com.alodiga.wallet.common.exception.NullParameterException ex) {
                java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
                response.setCode(ResponseCode.INTERNAL_ERROR.getCodigo());
                response.setMessage(ResponseCode.INTERNAL_ERROR.getMessage());
                response.setValid(false);
                return response;
            }

            if (transactionType == null) {
                //No existe limite se asume que no hay limite
                response.setCode(ResponseCode.SUCESS.getCodigo());
                response.setMessage(ResponseCode.SUCESS.getMessage());
                response.setValid(true);
                return response;
            }

            try {

                PreferencesEJB preferenceEJB = (PreferencesEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.PREFERENCES_EJB);
                EJBRequest request = new EJBRequest();
                Map<String, Object> params = new HashMap();
                params.put(QueryConstants.PARAM_BUSSINESS_ID, business.getId());
                params.put(QueryConstants.CLASSIFICATION_ID, 2L);
                params.put(QueryConstants.PARAM_PRODUCT_ID, productId);
                params.put(QueryConstants.PARAM_TRANSACTION_TYPE_ID, transactionType.getId());
                request.setParams(params);
                List<Float> sales = getEntireSalesAmountByBusiness(business, serviceType.getOperationType());
                Float maxAmountDaily;
                Float maxAmountMonth;
                Float maxAmountYearly;

                Map<Long, String> preferencesBussiness = preferenceEJB.getLastPreferenceValuesByBusiness(request);
                PreferenceManager pManager = PreferenceManager.getInstance();
                Map<Long, String> preferencesGlobal = pManager.getPreferences();

                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()) != null) {
                    maxAmountDaily = Float.parseFloat(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()));
                } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()) != null) {
                    maxAmountDaily = Float.parseFloat(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_DAILY_LIMIT_ID.getId()));
                } else {
                    maxAmountDaily = -1F;
                }
                if (maxAmountDaily != -1 && (sales.get(0) + amount) > maxAmountDaily) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_AMOUNT_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_AMOUNT_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }
                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()) != null) {
                    maxAmountMonth = Float.parseFloat(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()));
                } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()) != null) {
                    maxAmountMonth = Float.parseFloat(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getId()));
                } else {
                    maxAmountMonth = -1F;
                }
                if (maxAmountMonth != -1 && (sales.get(1) + amount) > maxAmountMonth) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_AMOUNT_MONTH_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }

                if (preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()) != null) {
                    maxAmountYearly = Float.parseFloat(preferencesBussiness.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()));
                } else if (preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()) != null) {
                    maxAmountYearly = Float.parseFloat(preferencesGlobal.get(PreferenceFieldEnum.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getId()));
                } else {
                    maxAmountYearly = -1F;
                }

                if (maxAmountYearly != -1 && (sales.get(2) + amount) > maxAmountYearly) {
                    response.setCode(ResponseCode.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getCodigo());
                    response.setMessage(ResponseCode.MAX_TRANSACTION_AMOUNT_YEAR_LIMIT_ID.getMessage());
                    response.setValid(false);
                    return response;
                }
                response.setCode(ResponseCode.SUCESS.getCodigo());
                response.setMessage(ResponseCode.SUCESS.getMessage());
                response.setValid(true);
            } catch (com.alodiga.wallet.common.exception.GeneralException ex) {
                java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
                response.setCode(ResponseCode.INTERNAL_ERROR.getCodigo());
                response.setMessage(ResponseCode.INTERNAL_ERROR.getMessage());
                response.setValid(false);
            } catch (RegisterNotFoundException | com.alodiga.wallet.common.exception.NullParameterException | EmptyListException ex) {
                java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
                response.setCode(ResponseCode.INTERNAL_ERROR.getCodigo());
                response.setMessage(ResponseCode.INTERNAL_ERROR.getMessage());
                response.setValid(false);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
                response.setCode(ResponseCode.INTERNAL_ERROR.getCodigo());
                response.setMessage(ResponseCode.INTERNAL_ERROR.getMessage());
                response.setValid(false);
            }
        }

        return response;
    }

    private List<Float> getEntireSalesAmountByBusiness(Business business, OperationType type) throws NullParameterException, GeneralException {
        List<Float> sales = new ArrayList();
        Date now = new Date();
        sales.add(getAmountBusinessIncomingTransactions(business, EjbUtils.getBeginningDate(now), EjbUtils.getEndingDate(now), type));
        sales.add(getAmountBusinessIncomingTransactions(business, EjbUtils.getBeginningDateMonth(now), now, type));
        sales.add(getAmountBusinessIncomingTransactions(business, EjbUtils.getBeginningDateAnnual(now), now, type));
        return sales;
    }

    private List<Integer> getEntireSalesQuantityByBusiness(Business bussiness, OperationType type) throws NullParameterException, GeneralException {
        List<Integer> salesQuantity = new ArrayList();
        Date now = new Date();
        salesQuantity.add((getQuantityBusinessIncomingTransactions(bussiness, EjbUtils.getBeginningDate(now), EjbUtils.getEndingDate(now), type)).intValue());
        salesQuantity.add((getQuantityBusinessIncomingTransactions(bussiness, EjbUtils.getBeginningDateMonth(now), now, type)).intValue());
        salesQuantity.add((getQuantityBusinessIncomingTransactions(bussiness, EjbUtils.getBeginningDateAnnual(now), now, type)).intValue());
        return salesQuantity;
    }

    private Float getAmountBusinessIncomingTransactions(Business business, Date startDate, Date endDate, OperationType type) throws GeneralException {
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
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        return transactionAmount;
    }

    private Long getQuantityBusinessIncomingTransactions(Business business, Date startDate, Date endDate, OperationType type) throws GeneralException {
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
            return entityManager.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public TransactionCommissionResponse getCommission(Business business, BusinessServiceType serviceType, long productId) {
        TransactionType transactionType = null;
        try {
            UtilsEJB utilsEJB = (UtilsEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.UTILS_EJB);
            //TODO cambiar esto para que sea una funcion directamente
            List<TransactionType> transactionTypes = utilsEJB.getTransactionType(new EJBRequest());
            for (TransactionType type : transactionTypes) {
                if (type.getCode().equals(serviceType.getPreferenceName())) {
                    transactionType = type;
                    break;
                }
            }

        } catch (EmptyListException | com.alodiga.wallet.common.exception.GeneralException | com.alodiga.wallet.common.exception.NullParameterException ex) {
            java.util.logging.Logger.getLogger(PreferencesData.class.getName()).log(Level.SEVERE, null, ex);
            return new TransactionCommissionResponse(ResponseCode.INTERNAL_ERROR.getCodigo(), ResponseCode.INTERNAL_ERROR.getMessage());
        }

        if (transactionType == null) {
            //No existe limite se asume que no hay limite
            return new TransactionCommissionResponse(ResponseCode.INTERNAL_ERROR.getCodigo(), ResponseCode.INTERNAL_ERROR.getMessage());
        }

        PreferencesEJB preferenceEJB = (PreferencesEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.PREFERENCES_EJB);
        EJBRequest request = new EJBRequest();
        Map<String, Object> params = new HashMap();
        params.put(QueryConstants.PARAM_BUSSINESS_ID, business.getId());
        params.put(QueryConstants.CLASSIFICATION_ID, 2L);
        params.put(QueryConstants.PARAM_PRODUCT_ID, productId);
        params.put(QueryConstants.PARAM_TRANSACTION_TYPE_ID, transactionType.getId());
        request.setParams(params);
        try {
            Map<Long, String> preferencesBussiness = preferenceEJB.getLastPreferenceValuesByBusiness(request);

            request = new EJBRequest();
            params = new HashMap();
            params.put(QueryConstants.PARAM_CODE, "BUSCOM");
            request.setParams(params);
            List<PreferenceField> fields = preferenceEJB.getPreferenceFieldsByCode(request);
            for (PreferenceField field : fields) {
                if (preferencesBussiness.containsKey(field.getId())) {
                    return new TransactionCommissionResponse(ResponseCode.SUCESS.getCodigo(), ResponseCode.SUCESS.getMessage(), Float.parseFloat(preferencesBussiness.get(field.getId())), true);
                }
            }

        } catch (Exception e) {
            return new TransactionCommissionResponse(ResponseCode.INTERNAL_ERROR.getCodigo(), ResponseCode.INTERNAL_ERROR.getMessage());
        }
        return new TransactionCommissionResponse(ResponseCode.INTERNAL_ERROR.getCodigo(), ResponseCode.INTERNAL_ERROR.getMessage());
    }

    public TransactionCommissionResponse getDiscountRate(Business business, long idTransactionType, long productId) {
        try {
            BusinessPortalEJB businessPortalEJB = (BusinessPortalEJB) EJBServiceLocator.getInstance().get(com.alodiga.wallet.common.utils.EjbConstants.BUSINESS_PORTAL_EJB);
            List<PreferenceValue> preferenceValue = businessPortalEJB.getDiscountRateByBusiness(business.getId(), productId, idTransactionType);

            for (PreferenceValue value : preferenceValue) {
                return new TransactionCommissionResponse(ResponseCode.SUCESS.getCodigo(), ResponseCode.SUCESS.getMessage(), Float.parseFloat(value.getValue()), true);
            }
        } catch (Exception e) {
            return new TransactionCommissionResponse(ResponseCode.INTERNAL_ERROR.getCodigo(), ResponseCode.INTERNAL_ERROR.getMessage());
        }
        return new TransactionCommissionResponse(ResponseCode.INTERNAL_ERROR.getCodigo(), ResponseCode.INTERNAL_ERROR.getMessage());
    }

}
