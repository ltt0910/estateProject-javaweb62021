package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override

    public List<UserEntity> getStaffs(Long buildingId) {
        Query sql = entityManager.createNativeQuery("select * from user inner join user_role " +
                "on user_role.userid = user.id inner join role on user_role.roleid = role.id  where role.code = 'staff'",UserEntity.class);
        List<UserEntity> result = sql.getResultList();
        return result;
    }

    public boolean setChecked(Long buildingId, Long staffId){
        Query sql = entityManager.createNativeQuery("select user.id , user.fullname " +
                "from user inner join assignmentbuilding on user.id = assignmentbuilding.staffid " +
                "inner join user_role on user.id = user_role.userid inner join role on user_role.roleid = role.id " +
                "where role.code = 'staff' and assignmentbuilding.buildingid = "+buildingId+" and user.id = "+staffId+" ",UserEntity.class);
        try {
            Object result =  sql.getSingleResult();
            if(result!=null){
                return true;
            }
        }catch (Exception e){

        }
            return false;
    }
}
