package com.portal.business.commons.data;

import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.exceptions.RegisterNotFoundException;
import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.generic.WsRequest;
import com.portal.business.commons.models.Business;
import com.portal.business.commons.models.BPPermission;
import com.portal.business.commons.models.BPPermissionGroup;
import com.portal.business.commons.models.BPPermissionHasProfile;
import com.portal.business.commons.models.BPProfile;
import com.portal.business.commons.models.BPUser;
import com.portal.business.commons.utils.EjbConstants;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

/**
 *
 * @author usuario
 */
public class UserData extends AbstractBusinessPortalWs {

    private static final Logger LOG = Logger.getLogger(UserData.class);

    public List<BPUser> getUsers(WsRequest request) throws EmptyListException, GeneralException {

        List<BPUser> users = null;
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPUser> cq = cb.createQuery(BPUser.class);
            Root<BPUser> from = cq.from(BPUser.class);
            cq.select(from);

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            users = query.getResultList();
        } catch (Exception ex) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), ex.getMessage()), null);
        }
        if (users.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return users;
    }

    public BPUser loadUser(WsRequest request) throws RegisterNotFoundException, NullParameterException, GeneralException {
        BPUser user = (BPUser) loadEntity(BPUser.class, request, LOG, getMethodName());

        return user;
    }

    public BPUser saveUser(WsRequest request) throws NullParameterException, GeneralException {
        return (BPUser) saveEntity(request, LOG, getMethodName());
    }

    public BPUser saveUser(BPUser user) throws NullParameterException, GeneralException {
        if (user == null) {
            throw new NullParameterException(LOG, sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "user"), null);
        }
        return (BPUser) saveEntity(user);
    }

    public List<BPPermissionGroup> getPermissionGroups() throws EmptyListException, NullParameterException, GeneralException {
        List<BPPermissionGroup> permissionGroups = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPPermissionGroup> cq = cb.createQuery(BPPermissionGroup.class);
            Root<BPPermissionGroup> from = cq.from(BPPermissionGroup.class);
            cq.select(from);
            
            cq.where(cb.equal(from.get("enabled"), true));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            permissionGroups = query.getResultList();

        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (permissionGroups == null || permissionGroups.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return permissionGroups;

    }

    public List<BPPermission> getPermissions() throws EmptyListException, NullParameterException, GeneralException {
        List<BPPermission> permissions = null;
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPPermission> cq = cb.createQuery(BPPermission.class);
            Root<BPPermission> from = cq.from(BPPermission.class);
            cq.select(from).where(cb.equal(from.get("enabled"), true));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            permissions = query.getResultList();

        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (permissions == null || permissions.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return permissions;
    }

    public List<BPPermission> getPermissionByGroupId(BPPermissionGroup permissionGroup) throws EmptyListException, NullParameterException, GeneralException {
        if (permissionGroup == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "permissionGroup"), null);
        }
        List<BPPermission> permissions = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPPermission> cq = cb.createQuery(BPPermission.class);
            Root<BPPermission> from = cq.from(BPPermission.class);
            cq.select(from);
            cq.where(cb.and(cb.equal(from.get("enabled"), true),
                    cb.equal(from.get("permissiongroup"), permissionGroup)));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            permissions = query.getResultList();

        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (permissions == null || permissions.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return permissions;
    }

    public List<BPPermission> getPermissionByProfileId(BPProfile profile) throws EmptyListException, NullParameterException, GeneralException {
        if (profile == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "groupId"), null);
        }
        List<BPPermission> permissions = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPPermission> cq = cb.createQuery(BPPermission.class);
            Root<BPPermissionHasProfile> from = cq.from(BPPermissionHasProfile.class);
            Join<BPPermissionHasProfile, BPPermission> permission = from.join("permission");
            cq.select(permission);
            cq.where(cb.and(cb.equal(permission.get("enabled"), true),
                    cb.equal(from.get("profile"), profile)));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            permissions = query.getResultList();

        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (permissions == null || permissions.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return permissions;
    }

    public BPPermission loadPermissionById(Long permissionId) throws GeneralException, NullParameterException, RegisterNotFoundException {
        WsRequest bRequest = new WsRequest(permissionId);
        BPPermission permission = (BPPermission) loadEntity(BPPermission.class, bRequest, LOG, getMethodName());
        return permission;

    }

    public List<BPProfile> getProfiles() throws EmptyListException, GeneralException {

        List<BPProfile> profiles = null;
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPProfile> cq = cb.createQuery(BPProfile.class);
            Root<BPProfile> from = cq.from(BPProfile.class);
            cq.select(from);
            cq.where(cb.equal(from.get("enabled"), true));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            profiles = query.getResultList();

        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (profiles == null || profiles.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return profiles;
    }

    public List<BPProfile> getOperatorsProfiles() throws EmptyListException, GeneralException {

        List<BPProfile> profiles = null;
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPProfile> cq = cb.createQuery(BPProfile.class);
            Root<BPProfile> from = cq.from(BPProfile.class);
            cq.select(from);
            cq.where(cb.and(cb.equal(from.get("enabled"), true),
                    cb.equal(from.get("isOperator"), true)));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            profiles = query.getResultList();

        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (profiles == null || profiles.isEmpty()) {
            throw new EmptyListException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), null);
        }
        return profiles;
    }

    public BPUser loadUserByLogin(String login) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (login == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "login"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPUser> cq = cb.createQuery(BPUser.class);
            Root<BPUser> from = cq.from(BPUser.class);
            cq.select(from);
            cq.where(cb.equal(from.get("login"), login));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (BPUser) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException(LOG, sysError.format(EjbConstants.ERR_EMPTY_LIST_EXCEPTION, this.getClass(), getMethodName()), ex);
        } catch (Exception ex) {
            ex.getMessage();
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), ex.getMessage()), ex);
        }

    }

    public BPUser loadUserByLogin(String login, String password) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (login == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "login"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPUser> cq = cb.createQuery(BPUser.class);
            Root<BPUser> from = cq.from(BPUser.class);

            cq.select(from).where(cb.and(cb.equal(from.get("login"), login), cb.equal(from.get("password"), password)));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (BPUser) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("");
        } catch (Exception ex) {
            ex.getMessage();
            throw new GeneralException("");
        }
    }

    public List<BPPermission> getPermissionByGroupIdAndProfile(BPPermissionGroup permissionGroup, BPProfile profile) throws EmptyListException, NullParameterException, GeneralException {
        if (permissionGroup == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "permissionGroup"), null);
        }
        if (profile == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "profile"), null);
        }
        List<BPPermission> permissions = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPPermission> cq = cb.createQuery(BPPermission.class);
            Root<BPPermissionHasProfile> from = cq.from(BPPermissionHasProfile.class);
            Join<BPPermissionHasProfile, BPPermission> permission = from.join("permission");

            cq.select(permission);
            cq.where(
                    cb.and(cb.and(
                            cb.equal(permission.get("permissionGroup"), permissionGroup),
                            cb.equal(permission.get("enabled"), true)),
                            cb.equal(from.get("profile"), profile)));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            permissions = query.getResultList();

        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (permissions == null || permissions.isEmpty()) {
            throw new EmptyListException("No");
        }
        return permissions;
    }

    public BPUser loadUserByEmail(String email) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (email == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "login"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPUser> cq = cb.createQuery(BPUser.class);
            Root<BPUser> from = cq.from(BPUser.class);

            cq.select(from).where(cb.and(cb.equal(from.get("email"), email), cb.equal(from.get("enabled"), true)));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (BPUser) query.getSingleResult();

        } catch (NoResultException ex) {
            throw new RegisterNotFoundException("No se encontro el usuario");
        } catch (Exception ex) {
            ex.getMessage();
            throw new GeneralException("No se encontro el usuario");
        }
    }

    public BPProfile loadProfile(Long profileId) throws RegisterNotFoundException, NullParameterException, GeneralException {
        if (profileId == null) {
            throw new NullParameterException(sysError.format(EjbConstants.ERR_NULL_PARAMETER, this.getClass(), getMethodName(), "profileId"), null);
        }
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<BPProfile> cq = cb.createQuery(BPProfile.class);
            Root<BPProfile> from = cq.from(BPProfile.class);

            cq.select(from).where(cb.equal(from.get("id"), profileId));

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            return (BPProfile) query.getSingleResult();

        } catch (NoResultException ex) {
            ex.printStackTrace();
            throw new RegisterNotFoundException("No se encontro el perfil");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("No se encontro el perfil");
        }
    }

    public BPProfile loadProfile(WsRequest request) throws RegisterNotFoundException, NullParameterException, GeneralException {
        BPProfile profile = (BPProfile) loadEntity(BPProfile.class, request, LOG, getMethodName());

        return profile;
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
            ex.printStackTrace();
            throw new RegisterNotFoundException("No se encontro el permiso");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeneralException("No se encontro el permiso");
        }
    }

    public List<Business> getBusinesses() throws EmptyListException, GeneralException {
        List<Business> business = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Business> cq = cb.createQuery(Business.class);
            Root<Business> from = cq.from(Business.class);

            cq.select(from);
            cq.where(
                    cb.equal(from.get("enabled"), true)
            );

            Query query = entityManager.createQuery(cq);
            query.setHint("toplink.refresh", "true");
            business = query.getResultList();

        } catch (Exception e) {
            throw new GeneralException(LOG, sysError.format(EjbConstants.ERR_GENERAL_EXCEPTION, this.getClass(), getMethodName(), e.getMessage()), null);
        }
        if (business == null || business.isEmpty()) {
            throw new EmptyListException("No");
        }
        return business;
    }

}
