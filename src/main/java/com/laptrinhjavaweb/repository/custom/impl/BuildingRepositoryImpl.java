package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private void buildingSearchBuilderPart1(BuildingSearchBuilder builder, StringBuilder sql){
        if (builder.getRentCostFrom()!=null) {
            sql.append(" and rentprice >= '" + builder.getRentCostFrom()+ "'");
        }
        if (builder.getRentCostTo()!=null) {
            sql.append(" and rentprice <= '" + builder.getRentCostTo()+ "'");
        }
        if (builder.getRentAreaFrom()!=null||builder.getRentAreaTo()!=null ) {
            sql.append(" and EXISTS (SELECT rentarea.value FROM rentarea WHERE 1 =1");
            if (builder.getRentAreaFrom()!=null) {
                sql.append(" and rentarea.value >= " + builder.getRentAreaFrom()+ "");
            }
            if (builder.getRentAreaTo()!=null) {
                sql.append(" and rentarea.value <= " + builder.getRentAreaTo() + "");
            }
            sql.append(")");
        }

        int i = 0;
        List<String> buildingTypes= new ArrayList<>(Arrays.asList(builder.getBuildingTypes()));
        if(buildingTypes.size()>0){
            sql.append(" and(");
            for (String item : buildingTypes) {
                buildingTypes.set(i,"building.type like '%"+item+"%'");
                i++;
            }
            String newSql = String.join(" OR ", buildingTypes);
            sql.append(newSql);
            sql.append(")");
        }
    }
    private void buildingSearchBuilderPart2(BuildingSearchBuilder builder, StringBuilder sql){
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field:fields){
                field.setAccessible(true);
                String name = field.getName();
                if((!name.equals("buildingTypes")) && (!name.startsWith("rentArea")) && (!name.startsWith("rentCost"))){
                    Object objectValue = field.get(builder);
                    if(objectValue!=null){
                        if(field.getType().getName().equals("java.lang.String")){
                            String value  = (String) objectValue;
                            if(value!=""){
                                sql.append(" AND "+name.toLowerCase()+" LIKE '%"+value+"%'");
                            }
                        }else if(field.getType().getName().equals("java.lang.Integer")){
                                Integer value = (Integer) objectValue;
                                sql.append(" AND "+name.toLowerCase()+" = "+value+"");

                        }
                    }
                }
            }
        }catch (Exception e){

        }
    }
    @Override
    public List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql1 = new StringBuilder("SELECT * ");
        StringBuilder sql2 = new StringBuilder(" FROM building ");
        StringBuilder sql3 = new StringBuilder(" WHERE 1=1");
        buildingSearchBuilderPart2(buildingSearchBuilder,sql3);
        buildingSearchBuilderPart1(buildingSearchBuilder,sql3);
        if (buildingSearchBuilder.getEmployee()!=null) {
            sql2.append(" inner join assignmentbuilding as a on a.buildingid = building.id ");
            sql3.append(" and a.staffid = " +buildingSearchBuilder.getEmployee() + "");
        }
        sql3.append(" group by building.id");
        String sql = (sql1.toString() + sql2.toString() + sql3.toString());
        Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
        List<BuildingEntity> buildingEntities =query.getResultList();
        return buildingEntities;
    }
    @Override
    public List<BuildingEntity> findBuildingAssignmentByStaff(BuildingSearchBuilder buildingSearchBuilder,Long id) {

        Query query = entityManager.createNativeQuery("select * from building as b inner join assignmentbuilding as a on b.id = a.buildingid where a.staffid = "+id+"", BuildingEntity.class);
        List<BuildingEntity> buildingEntities = query.getResultList();
        return buildingEntities;
    }


    public BuildingEntity findOne(Long id){
        Query query = entityManager.createNativeQuery("select * from building where id = "+id+"", BuildingEntity.class);
        return (BuildingEntity) query.getSingleResult();
    }

    public List<UserEntity> getStaffs() {
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
