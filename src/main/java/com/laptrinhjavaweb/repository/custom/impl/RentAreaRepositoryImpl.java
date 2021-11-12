package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;

@Component
public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager ;

    @Override
    @Transactional
    public void addRentArea(RentAreaEntity rentareas) {
        Query query = entityManager.createNativeQuery("INSERT INTO rentarea (value,buildingid) VALUES ("+rentareas.getValue()+","+rentareas.getBuilding().getId()+")");
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteRentArea(Long id) {
        Query query = entityManager.createNativeQuery("DELETE from rentarea where buildingid= "+id+"");
        query.executeUpdate();
    }
}
