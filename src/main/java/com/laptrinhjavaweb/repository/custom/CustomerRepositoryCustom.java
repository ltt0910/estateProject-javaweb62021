package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<UserEntity> getStaffs();
    List<CustomerEntity> searchCustomer(CustomerEntity customerEntity,Long staffId);
}
