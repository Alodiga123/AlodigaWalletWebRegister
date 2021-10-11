package com.portal.business.commons.generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import javax.persistence.Query;
import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.exceptions.RegisterNotFoundException;
import com.portal.business.commons.utils.EjbConstants;
import com.portal.business.commons.utils.MessageFormatHelper;
import com.portal.business.commons.utils.WsConstants;


public class AbstractBusinessPortalWs {
    protected EntityManager entityManager;
    protected EntityManagerWrapper entityManagerWrapper = null;
    protected MessageFormatHelper sysMessages, sysError;
    protected EntityManagerFactory emf = null;
    public AbstractBusinessPortalWs() {
        emf = Persistence.createEntityManagerFactory("BusinessPortalPu");
        entityManager = emf.createEntityManager();
        entityManagerWrapper = new EntityManagerWrapper(entityManager);
        sysMessages = new MessageFormatHelper(EjbConstants.MESSAGE_FILE_NAME);
        sysError = new MessageFormatHelper(EjbConstants.ERROR_FILE_NAME);
    }

    //@Override
    @PostConstruct
    public void init() {
        entityManagerWrapper.setEntityManager(entityManager);
    }

    public boolean echo() throws Exception {
        return true;
    }

    protected String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public EntityManagerWrapper getEntityManagerWrapper() {
        return entityManagerWrapper;
    }

    protected Boolean removeEntity(Object entity, Logger logger, String methodName) {
//    	FIXME utilizar el AUDIT DATA
        List<Object> entities = (List<Object>) entity;
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            for (Object o : entities) {
                entityManager.remove(entity);
            }
            transaction.commit();
        } finally {
            return false;
        }
    }

    protected Object saveEntity(Object entity, Logger logger, String methodName) throws GeneralException, NullParameterException {
        if (entity == null) {
            throw new NullParameterException(logger, sysError.format(WsConstants.ERR_NULL_PARAMETER, this.getClass(), methodName, "Entity"), null);
        }
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (((RemittenceGenericEntity) entity).getPk() != null) {
                //processAuditData(EventType.UPDATE, entity, auditData, entityManagerWrapper);
                entityManagerWrapper.update(entity);
            } else {
                entityManagerWrapper.save(entity);
                //processAuditData(EventType.CREATE, entity, auditData, entityManagerWrapper);
            }

            transaction.commit();
        } catch (Exception e) {
            try {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            } catch (IllegalStateException e1) {
                throw new GeneralException(logger,e.getMessage(), e);
            } catch (SecurityException e1) {
                throw new GeneralException(logger, e.getMessage(), e);
            }
            throw new GeneralException(logger,  e.getMessage(), e);
        }

        return entity;
    }

    protected Object listEntities(Class clazz, Integer first, Integer limit, Logger logger, String methodName) throws GeneralException, EmptyListException {
        Object oReturns = null;
        try {
            oReturns = entityManagerWrapper.get(clazz, first, limit);
            
            //persistAuditData(EventType.READ, oReturns, auditData);

        } catch (Exception e) {
            throw new GeneralException(logger, e.getMessage(), e);
        }
        if (((List) oReturns).isEmpty()) {
            throw new EmptyListException(logger, WsConstants.ERR_EMPTY_LIST_EXCEPTION, null);
        }
        return oReturns;
    }

    protected Object loadEntity(Class clazz, Object pk, Logger logger, String methodName) throws NullParameterException, GeneralException, RegisterNotFoundException {
        Object oReturn = null;
        if (pk == null) {
            String error = WsConstants.ERR_NULL_PARAMETER;
            throw new NullParameterException(logger, error, null);
        }
        try {
            oReturn = entityManagerWrapper.load(clazz, pk);
            //persistAuditData(EventType.READ, oReturn, auditData);
        } catch (Exception e) {
            throw new GeneralException(logger,e.getMessage(), e);
        }
        if (oReturn == null) {
            throw new RegisterNotFoundException(logger, WsConstants.ERR_REGISTER_NOT_FOUND_EXCEPTION, null);
        }
        return oReturn;
    }

    protected Object getNamedQueryResult(Class clazz, String namedQueryName, Map<? extends Object, ? extends Object> map, String method, Logger logger, String returnObjectName, Integer first, Integer limit) throws EmptyListException, GeneralException {
        Object object = null;

        if (map == null) {
            map = new HashMap<Object, Object>();
        }
        try {
            object = (Object) entityManagerWrapper.findByNamedQuery(namedQueryName, map, first, limit);
            //persistAuditData(EventType.READ, object, auditData);

        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(logger,e.getMessage(), e);
        }
        if (object == null || ((List<Object>) object).size() < 1) {
            throw new EmptyListException(logger, WsConstants.ERR_EMPTY_LIST_EXCEPTION, null);
        }

        return object;
    }


    protected Object removeEntity(WsRequest request, Logger logger, String methodName) throws GeneralException, NullParameterException {
        if (request == null) {
            throw new NullParameterException(logger, "", null);
        }
        return removeEntity(request.getParam(), logger, methodName);
    }

    protected Object saveEntity(WsRequest request, Logger logger, String methodName) throws GeneralException, NullParameterException {
        if (request == null) {
            throw new NullParameterException(logger, "", null);
        }
        return saveEntity(request.getParam(), logger, methodName);
    }

    protected Object listEntities(Class clazz, WsRequest request, Logger logger, String methodName) throws GeneralException, EmptyListException, NullParameterException {
        if (request == null) {
            throw new NullParameterException(logger, "", null);
        }
        return listEntities(clazz, request.getFirst(), request.getLimit(), logger, methodName);
    }

    protected Object loadEntity(Class clazz, WsRequest request, Logger logger, String methodName) throws NullParameterException, GeneralException, RegisterNotFoundException {
        if (request == null) {
            throw new NullParameterException(logger, "", null);
        }
        return loadEntity(clazz, request.getParam(), logger, methodName);
    }

    protected Object getNamedQueryResult(Class clazz, String namedQueryName, WsRequest request, String method, Logger logger, String returnObjectName) throws EmptyListException, GeneralException, NullParameterException {
        if (request == null) {
            throw new NullParameterException(logger, "", null);
        }
        return getNamedQueryResult(clazz, namedQueryName, request.getParams(), method, logger, returnObjectName, request.getFirst(), request.getLimit());
    }

   

    public void executeNameQuery(Class clazz, String namedQueryName, Map<? extends Object, ? extends Object> map, String method, Logger logger, String returnObjectName, Integer first, Integer limit) throws EmptyListException, GeneralException {

        if (map == null) {
            map = new HashMap<Object, Object>();
        }
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManagerWrapper.executeNamedQuery(namedQueryName, map);
            transaction.commit();
        } catch (Exception e) {
            throw new GeneralException(logger,  e.getMessage(), e);
        }
    }

    public void executeQuery(Query query) throws NullParameterException,EmptyListException, GeneralException {

        if (query == null) {
            throw new NullParameterException("sql cannot be null");
        }
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new GeneralException(e.getMessage());
        }
    }

    public Query createQuery(String sql) throws NullParameterException, Exception {
        Query query = null;
        if (sql == null) {
            throw new NullParameterException("sql cannot be null");
        }
        try {
            query = entityManagerWrapper.createQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return query;
    }

    protected Object createSearchQuery(Class clazz, WsRequest request, Map orderMap, Logger logger, String methodName, String returnObjectName, boolean isFilter) throws GeneralException, EmptyListException, NullParameterException {
        Object object = null;
        Map map = request.getParams();
        if (map == null) {
            throw new NullParameterException(logger, "Map data is empty", null);
        }
        try {
            //System.out.println(".........A1.........."+(new Date()));
            object = (Object) entityManagerWrapper.executeSearchQuery(clazz, map, orderMap, request.getFirst(), request.getLimit(), isFilter);
            //System.out.println(".........A2.........."+(new Date()));
            //persistAuditData(EventType.READ, object, request.getAuditData());
            //System.out.println(".........A3.........."+(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(logger,  e.getMessage(), e);
        }
        if (object == null || ((List<Object>) object).size() < 1) {
            throw new EmptyListException(logger, WsConstants.ERR_EMPTY_LIST_EXCEPTION, null);
        }
        return object;
    }

    //Este metodo se utiliza para que el procesamiento sea mas rapido pero no guardara registro en la bitacora.
    protected  Object saveEntity(Object entity) throws GeneralException, NullParameterException {
        if (entity == null) {
            throw new NullParameterException("NullParameterException ");
        }
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive())
                 transaction.begin();
            if (((RemittenceGenericEntity) entity).getPk() != null) {
                //processAuditData(EventType.UPDATE, entity, auditData, entityManagerWrapper);
                entityManagerWrapper.update(entity);
            } else {
                entityManagerWrapper.save(entity);
                //processAuditData(EventType.CREATE, entity, auditData, entityManagerWrapper);
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

        return entity;
    }
    
    
    public Object load(Class clazz, Object pk) throws NullParameterException, GeneralException, RegisterNotFoundException {
        Object oReturn = null;
        if (pk == null) {
            String error = WsConstants.ERR_NULL_PARAMETER;
            throw new NullParameterException("Pk required");
        }
        try {
            oReturn = entityManagerWrapper.load(clazz, pk);
            //persistAuditData(EventType.READ, oReturn, auditData);
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
        if (oReturn == null) {
            throw new RegisterNotFoundException(WsConstants.ERR_REGISTER_NOT_FOUND_EXCEPTION);
        }
        return oReturn;
    }
    
}
