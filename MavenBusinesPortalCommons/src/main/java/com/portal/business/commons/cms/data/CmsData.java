package com.portal.business.commons.cms.data;

import com.portal.business.commons.cms.CmsCard;
import com.portal.business.commons.cms.CmsCity;
import com.portal.business.commons.cms.CmsCountry;
import com.portal.business.commons.cms.CmsDocumentPersonType;
import com.portal.business.commons.cms.CmsNaturalCustomer;
import com.portal.business.commons.cms.CmsPersonType;
import com.portal.business.commons.cms.CmsState;
import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.exceptions.RegisterNotFoundException;
import com.portal.business.commons.generic.EntityManagerWrapper;
import com.portal.business.commons.models.Operator;
import com.portal.business.commons.utils.EjbConstants;
import com.portal.business.commons.utils.MessageFormatHelper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

/**
 *
 * @author henry
 */
public class CmsData {

    private static final Logger LOG = Logger.getLogger(CmsData.class);

    protected MessageFormatHelper sysError;

    protected EntityManagerFactory emf;

    private final EntityManager entityManager;

    protected EntityManagerWrapper entityManagerWrapper;

    public CmsData() {
        emf = Persistence.createEntityManagerFactory("cmsPU");
        entityManager = emf.createEntityManager();
        sysError = new MessageFormatHelper(EjbConstants.ERROR_FILE_NAME);
        entityManagerWrapper = new EntityManagerWrapper(entityManager);
    }

    protected String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public List<CmsCountry> getCountries() throws GeneralException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsCountry> cq = cb.createQuery(CmsCountry.class);
            Root<CmsCountry> from = cq.from(CmsCountry.class);
            cq.select(from);
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<CmsState> getStates(CmsCountry country) throws NullParameterException, GeneralException {
        if (country == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "country"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsState> cq = cb.createQuery(CmsState.class);
            Root<CmsState> from = cq.from(CmsState.class);
            cq.select(from).where(cb.equal(from.get("country"), country));
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<CmsCity> getCities(CmsState state) throws NullParameterException, GeneralException {
        if (state == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "state"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsCity> cq = cb.createQuery(CmsCity.class);
            Root<CmsCity> from = cq.from(CmsCity.class);
            cq.select(from).where(cb.equal(from.get("state"), state));
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<CmsPersonType> getPersonTypes(CmsCountry country) throws NullParameterException, GeneralException {
        if (country == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "country"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsPersonType> cq = cb.createQuery(CmsPersonType.class);
            Root<CmsPersonType> from = cq.from(CmsPersonType.class);
            cq.select(from).where(cb.equal(from.get("country"), country));
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    private List<CmsPersonType> getPersonType(CmsCountry country, boolean natural) throws NullParameterException, GeneralException {
        if (country == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "country"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsPersonType> cq = cb.createQuery(CmsPersonType.class);
            Root<CmsPersonType> from = cq.from(CmsPersonType.class);
            cq.select(from).where(cb.equal(from.get("country"), country), cb.equal(from.get("indNaturalPerson"), natural));
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<CmsDocumentPersonType> getDocumentTypes(CmsCountry country) throws NullParameterException, GeneralException {
        if (country == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "country"), null);
        }
        try {
            List<CmsPersonType> personTypes = getPersonType(country, true);
            List<CmsDocumentPersonType> documentTypes = new ArrayList();

            for (CmsPersonType personType : personTypes) {
                CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                CriteriaQuery<CmsDocumentPersonType> cq = cb.createQuery(CmsDocumentPersonType.class);
                Root<CmsDocumentPersonType> from = cq.from(CmsDocumentPersonType.class);
                cq.select(from).where(cb.equal(from.get("personType"), personType));
                documentTypes.addAll(entityManager.createQuery(cq).getResultList());
            }
            return documentTypes;
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public List<CmsDocumentPersonType> getDocumentTypes(CmsPersonType personType) throws NullParameterException, GeneralException {
        if (personType == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), "personType"), null);
        }
        try {
            List<CmsDocumentPersonType> documentTypes = new ArrayList();

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsDocumentPersonType> cq = cb.createQuery(CmsDocumentPersonType.class);
            Root<CmsDocumentPersonType> from = cq.from(CmsDocumentPersonType.class);
            cq.select(from).where(cb.equal(from.get("personType"), personType));
            documentTypes.addAll(entityManager.createQuery(cq).getResultList());

            return documentTypes;
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
    }

    public CmsCountry getCountry(Long idCountry) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (idCountry == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "idCountry"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsCountry> cq = cb.createQuery(CmsCountry.class);
            Root<CmsCountry> from = cq.from(CmsCountry.class);

            cq.select(from).where(cb.equal(from.get("id"), idCountry));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (CmsCountry) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("No se encontro el pais");
        } catch (Exception ex) {
            ex.getMessage();
            throw new GeneralException("No se encontro el pais");
        }
    }

    public CmsState getState(Long idState) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (idState == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "idState"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsState> cq = cb.createQuery(CmsState.class);
            Root<CmsState> from = cq.from(CmsState.class);

            cq.select(from).where(cb.equal(from.get("id"), idState));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (CmsState) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("No se encontro el estado");
        } catch (Exception ex) {
            ex.getMessage();
            throw new GeneralException("No se encontro el estado");
        }
    }

    public CmsCity getCity(Long idCity) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (idCity == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "idCity"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsCity> cq = cb.createQuery(CmsCity.class);
            Root<CmsCity> from = cq.from(CmsCity.class);

            cq.select(from).where(cb.equal(from.get("id"), idCity));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (CmsCity) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("No se encontro la ciudad");
        } catch (Exception ex) {
            ex.getMessage();
            throw new GeneralException("No se encontro la ciudad");
        }
    }

    public CmsPersonType getPersonType(Long idPersonType) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (idPersonType == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "idPersonType"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsPersonType> cq = cb.createQuery(CmsPersonType.class);
            Root<CmsPersonType> from = cq.from(CmsPersonType.class);

            cq.select(from).where(cb.equal(from.get("id"), idPersonType));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (CmsPersonType) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("No se encontro el tipo de persona");
        } catch (Exception ex) {
            ex.getMessage();
            throw new GeneralException("No se encontro el tipo de person");
        }
    }

    public CmsDocumentPersonType getDocumentPersonType(Long idDocumentPersonType) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (idDocumentPersonType == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "idDocumentPersonType"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsDocumentPersonType> cq = cb.createQuery(CmsDocumentPersonType.class);
            Root<CmsDocumentPersonType> from = cq.from(CmsDocumentPersonType.class);

            cq.select(from).where(cb.equal(from.get("id"), idDocumentPersonType));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (CmsDocumentPersonType) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("No se encontro el documento de persona");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("No se encontro el documento de persona");
        }
    }

    public CmsCard getCardByNumber(String cardNumber) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (cardNumber == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "cardNumber"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsCard> cq = cb.createQuery(CmsCard.class);
            Root<CmsCard> from = cq.from(CmsCard.class);
            cq.select(from).where(cb.equal(from.get("cardNumber"), cardNumber));
            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (CmsCard) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("Card Number not found");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("Database Error");
        }
    }

    public List<CmsNaturalCustomer> getAssociatedCustomer(CmsNaturalCustomer customer) throws EmptyListException, GeneralException {

        List<CmsNaturalCustomer> customerList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsNaturalCustomer> cq = cb.createQuery(CmsNaturalCustomer.class);
            Root<CmsNaturalCustomer> from = cq.from(CmsNaturalCustomer.class);
            customerList = entityManager.createQuery(cq.select(from).where(cb.equal(from.get("naturalCustomer"), customer))).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (customerList == null || customerList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return customerList;
    }
    
    public List<CmsCard> getCardByCustomer(CmsNaturalCustomer customer) throws EmptyListException, GeneralException {

        List<CmsCard> cardList = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<CmsCard> cq = cb.createQuery(CmsCard.class);
            Root<CmsCard> from = cq.from(CmsCard.class);
            cardList = entityManager.createQuery(cq.select(from).where(cb.equal(from.get("naturalCustomer"), customer))).getResultList();
        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (cardList == null || cardList.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return cardList;
    }

}
