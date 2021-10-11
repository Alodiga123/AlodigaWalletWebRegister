package com.portal.business.commons.managers;

//import com.alodiga.multilevelchannel.commons.ejbs.UserEJB;
//import com.alodiga.multilevelchannel.commons.exceptions.EmptyListException;
//import com.alodiga.multilevelchannel.commons.exceptions.NullParameterException;
//import com.alodiga.multilevelchannel.commons.models.Permission;
//import com.alodiga.multilevelchannel.commons.models.PermissionGroup;
//import com.alodiga.multilevelchannel.commons.models.Profile;
//import com.alodiga.multilevelchannel.commons.utils.EJBServiceLocator;
//import com.alodiga.multilevelchannel.commons.utils.EjbConstants;
import com.portal.business.commons.data.UserData;
import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.models.BPPermission;
import com.portal.business.commons.models.BPPermissionGroup;
import com.portal.business.commons.models.BPProfile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class PermissionManager {

    private static PermissionManager instance;
    private List<BPPermissionGroup> permissionGroups;
    private List<BPPermission> permissions;
//    private UserEJB userEJB;
    private Map<Long, List<BPPermission>> permissionByGroup;
    private Map<Long, List<BPPermission>> permissionByProfile;

    public static synchronized PermissionManager getInstance() throws Exception {
        if (instance == null) {
            instance = new PermissionManager();
        }
        return instance;
    }

    public static void refresh() throws Exception {
        instance = new PermissionManager();
    }

    public PermissionManager(BPProfile profile) throws Exception {
        UserData userdata = new UserData();

        permissionGroups = userdata.getPermissionGroups();
        permissions = userdata.getPermissions();

        permissionByGroup = new HashMap<Long, List<BPPermission>>();
        permissionByProfile = new HashMap<Long, List<BPPermission>>();
        List<BPPermission> ps = null;
        for (BPPermissionGroup permissionGroup : permissionGroups) {
            try {
                ps = userdata.getPermissionByGroupIdAndProfile(permissionGroup, profile);
                permissionByGroup.put(permissionGroup.getId(), ps);
            } catch (EmptyListException e) {
            }
        }
        permissions = userdata.getPermissions();
        List<BPProfile> profiles = null;
        try {
            profiles = userdata.getProfiles();
            for (BPProfile prof : profiles) {
                try {
                    ps = userdata.getPermissionByProfileId(prof);
                    permissionByProfile.put(prof.getId(), ps);
                } catch (EmptyListException e) {
//                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public PermissionManager() throws Exception {
        UserData userdata = new UserData();
        List<BPProfile> profiles = userdata.getProfiles();
        permissionGroups = userdata.getPermissionGroups();
        permissions = userdata.getPermissions();

        permissionByGroup = new HashMap();
        permissionByProfile = new HashMap();
        List<BPPermission> ps;
        for (BPPermissionGroup permissionGroup : permissionGroups) {
            try {
                ps = userdata.getPermissionByGroupIdAndProfile(permissionGroup, profiles.get(0));
                permissionByGroup.put(permissionGroup.getId(), ps);
            } catch (EmptyListException e) {
            }
        }
        permissions = userdata.getPermissions();
        try {
            for (BPProfile profile : profiles) {
                try {
                    ps = userdata.getPermissionByProfileId(profile);
                    permissionByProfile.put(profile.getId(), ps);
                } catch (EmptyListException e) {
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<BPPermission> getPermissionByGroupId(Long groupId) throws NullParameterException {
        if (groupId == null) {
            throw new NullParameterException("Parameter groupId cannot be null");
        }
        return permissionByGroup.get(groupId);
    }

    public List<BPPermissionGroup> getPermissionGroups() throws NullParameterException {
        return permissionGroups;
    }
//
//    public List<Permission> getPermissions() throws NullParameterException {
//        return permissions;
//    }
//
//    public Permission getPermissionById(Long permissionId) throws NullParameterException {
//        if (permissionId == null) {
//            throw new NullParameterException("Parameter permissionId cannot be null");
//        }
//        for (Permission permission : permissions) {
//            if (permission.getId().equals(permissionId)) {
//                return permission;
//            }
//        }
//        return null;
//    }
//

    public BPPermissionGroup getPermissionGroupById(Long permissionGroupId) throws NullParameterException {
        if (permissionGroupId == null) {
            throw new NullParameterException("Parameter permissionGroupId cannot be null");
        }
        for (BPPermissionGroup permissionGroup : permissionGroups) {
            if (permissionGroup.getId().equals(permissionGroupId)) {
                return permissionGroup;
            }
        }
        return null;
    }

//    public List<Permission> getPermissionByProfileId(Long profileId) throws NullParameterException {
//        if (profileId == null) {
//            throw new NullParameterException("Parameter profileId cannot be null");
//        }
//        return permissionByProfile.get(profileId);
//    }
//
//    public boolean hasPermisssion(Long profileId, Long permissionId) throws NullParameterException {
//        if (profileId == null) {
//            throw new NullParameterException("Parameter profileId cannot be null");
//        }else  if (permissionId == null) {
//            throw new NullParameterException("Parameter permissionId cannot be null");
//        }
//         for(Permission permission : permissionByProfile.get(profileId)){
//            if(permission.getId().equals(permissionId)){
//            return true;
//            }
//         }
//         return false;
//    }
}
