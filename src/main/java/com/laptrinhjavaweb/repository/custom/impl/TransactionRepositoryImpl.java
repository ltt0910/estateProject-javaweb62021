package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.repository.custom.TransactionRepositoryCustom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager ;

    @Override
    public List<TransactionEntity> getTransactionByCustomer(Long customerId) {
        Query sql = entityManager.createNativeQuery("select * from transaction where customerid = "+customerId+"", TransactionEntity.class);
        List<TransactionEntity> result = sql.getResultList();
        return result;

    }

}
