package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Component
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> getStaffs() {
        Query sql = entityManager.createNativeQuery("select * from user inner join user_role " +
                "on user_role.userid = user.id inner join role on user_role.roleid = role.id  where role.code = 'staff'",UserEntity.class);
        List<UserEntity> result = sql.getResultList();
        return result;
    }

    @Override
    public List<CustomerEntity> searchCustomer(CustomerEntity customerEntity,Long staffId) {
        StringBuilder sql1 = new StringBuilder("SELECT * ");
        StringBuilder sql2 = new StringBuilder(" FROM customer ");
        StringBuilder sql3 = new StringBuilder(" WHERE 1=1");
        if(!StringUtils.isNullOrEmty(customerEntity.getFullName())){
            sql3.append(" and fullname like '%"+customerEntity.getFullName()+"%'");
        }
        if(!StringUtils.isNullOrEmty(customerEntity.getPhone())){
            sql3.append(" and phone like '%"+customerEntity.getPhone()+"%'");
        }
        if(!StringUtils.isNullOrEmty(customerEntity.getEmail())){
            sql3.append(" and email like '%"+customerEntity.getEmail()+"%'");
        }
        if (staffId!=null) {
            sql2.append(" inner join assignmentcustomer as a on a.customerid = customer.id ");
            sql3.append(" and a.staffid = " +staffId + "");
        }
        sql3.append(" group by customer.id");
        String sql = (sql1.toString() + sql2.toString() + sql3.toString());
        Query query = entityManager.createNativeQuery(sql, CustomerEntity.class);
        List<CustomerEntity> customerEntities =query.getResultList();
        return customerEntities;
    }
}
