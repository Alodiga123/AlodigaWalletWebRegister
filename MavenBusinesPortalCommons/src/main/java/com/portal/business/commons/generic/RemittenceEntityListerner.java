package com.portal.business.commons.generic;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;


public class RemittenceEntityListerner {

    @PrePersist
    public void onEntityPrePersist(Object entity) {
    	System.out.println("Persistiendo " + entity.getClass());
    }

    @PostPersist
    public void onEntityPostPersist(Object entity) {
    	System.out.println("Post Persistiendo " + entity.getClass());
    }

    @PreRemove
    public void onEntityPreRemove(Object entity) {
    	System.out.println("Pre remove " + entity.getClass());
    }

    @PostRemove
    public void onEntityPostRemove(Object entity) {
    	System.out.println("Post Remove " + entity.getClass());
    }

    @PreUpdate
    public void onEntityPreUpdate(Object entity) {
    	System.out.println("Pre Update " + entity.getClass());
    }

    @PostUpdate
    public void onEntityPostUpdate(Object entity) {
    	System.out.println("Post Update " + entity.getClass());
    }

    @PostLoad
    public void onEntityLoad(Object entity) {
        System.out.println("Load " + entity.getClass());
    }

  

    public static boolean isInstanceEntity(Object entity) {
        return entity instanceof RemittenceEntity;
    }
}
