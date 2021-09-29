package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Component
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BuildingEntity> searchBuilding(Map<String,Object> params,List<String> buildingTypes) {
        String name = (String) params.get("name");
        String numberofbasement = (String) params.get("numberOfBasement");
        StringBuilder sql1 = new StringBuilder("SELECT * ");
        StringBuilder sql2 = new StringBuilder(" FROM building ");
        StringBuilder sql3 = new StringBuilder(" WHERE 1=1");
        if (!StringUtils.isNullOrEmty(name)){
            sql3.append(" AND name LIKE '%"+name+"%'");
        }
        if (!StringUtils.isNullOrEmty(numberofbasement)) {
            sql3.append(" AND numberofbasement = " + numberofbasement + "");
        }

        if(!StringUtils.isNullOrEmty((String) params.get("districtCode"))){
            sql3.append(" AND district like '" + params.get("districtCode") + "'");
        }
        if (params.get("costRentFrom")!= null && params.get("costRentFrom")!="") {
            sql3.append(" and rentprice >= '" + params.get("costRentFrom")+ "'");
        }
        if (params.get("costRentTo") != null&& params.get("costRentTo")!="") {
            sql3.append(" and rentprice <= '" + params.get("costRentTo") + "'");
        }
        if (!StringUtils.isNullOrEmty((String) params.get("street"))) {
            sql3.append(" and street like '%" + params.get("street")  + "%'");
        }
        if (!StringUtils.isNullOrEmty((String) params.get("ward"))) {
            sql3.append(" and ward like '%" + params.get("ward")  + "%'");
        }
        if (!StringUtils.isNullOrEmty((String) params.get("managerName"))) {
            sql3.append(" and manager_name like '%" + params.get("managerName") + "%'");
        }

        if (!StringUtils.isNullOrEmty((String) params.get("managerPhone"))) {
            sql3.append(" and manager_phone like '%" + params.get("managerPhone")  + "%'");
        }
        if (!StringUtils.isNullOrEmty((String) params.get("areaRentFrom")) || !StringUtils.isNullOrEmty((String) params.get("areaRentTo"))) {
            sql3.append(" and EXISTS (SELECT rentarea.value FROM rentarea WHERE 1 =1");
            if (!StringUtils.isNullOrEmty((String) params.get("areaRentFrom"))) {
                sql3.append(" and rentarea.value >= " + params.get("areaRentFrom") + "");
            }
            if (!StringUtils.isNullOrEmty((String) params.get("areaRentTo"))) {
                sql3.append(" and rentarea.value <= " + params.get("areaRentTo") + "");
            }
            sql3.append(")");
        }

        int i = 0;
        if(buildingTypes.size()>0){
            sql3.append(" and(");
            for (String item : buildingTypes) {
                buildingTypes.set(i,"building.type like '%"+item+"%'");
                i++;
            }
            String newSql = String.join(" OR ", buildingTypes);
            sql3.append(newSql);
            sql3.append(")");
        }
        if (!StringUtils.isNullOrEmty((String) params.get("staffId"))) {
            sql2.append(" inner join assignmentbuilding as a on a.buildingid = building.id ");
            sql3.append(" and a.staffid = " + params.get("staffId") + "");
        }
        sql3.append(" group by building.id");
        String sql = (sql1.toString() + sql2.toString() + sql3.toString());
        Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
        List<BuildingEntity> buildingEntities =query.getResultList();
        return buildingEntities;
    }

    @Override
    public List<BuildingEntity> findBuildingAssignmentByStaff(Map<String,Object> params,List<String> buildingTypes,Long id) {

        Query query = entityManager.createNativeQuery("select * from building as b inner join assignmentbuilding as a on b.id = a.buildingid where a.staffid = "+id+"", BuildingEntity.class);
        List<BuildingEntity> buildingEntities = query.getResultList();
        return buildingEntities;
    }


    public BuildingEntity findOne(Long id){
        Query query = entityManager.createNativeQuery("select * from building where id = "+id+"", BuildingEntity.class);
        return (BuildingEntity) query.getSingleResult();
    }

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
