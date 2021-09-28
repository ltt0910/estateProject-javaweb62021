package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class AssignmentBuildingRepositoryImpl implements AssignmentBuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteStaff(AssignmentBuildingDTO assignmentBuildingDTO, Long id) {
       Query sqlDelete =  entityManager.createNativeQuery("delete from assignmentbuilding where assignmentbuilding.buildingid = "+assignmentBuildingDTO.getBuildingId()+" and assignmentbuilding.staffid = " + id + "",AssignmentBuildingEntity.class);
       sqlDelete.executeUpdate();

    }
    @Override
    @Transactional
    public void addStaff(AssignmentBuildingDTO assignmentBuildingDTO, Long id) {
        Query sqlUpdate =  entityManager.createNativeQuery("insert into assignmentbuilding(staffid,buildingid) values("+id+","+assignmentBuildingDTO.getBuildingId()+")");
        sqlUpdate.executeUpdate();
    }

    @Override
    public List<UserEntity> staffList(Long buildingId) {
        Query sql = entityManager.createNativeQuery("select * from user " +
                "inner join assignmentbuilding on user.id = assignmentbuilding.staffid " +
                "inner join user_role on user.id = user_role.userid " +
                "inner join role on user_role.roleid = role.id " +
                "where role.code = 'staff' and assignmentbuilding.buildingid =" + buildingId+ "",UserEntity.class);
        List<UserEntity> userEntities = sql.getResultList();
        return userEntities ;
    }
}