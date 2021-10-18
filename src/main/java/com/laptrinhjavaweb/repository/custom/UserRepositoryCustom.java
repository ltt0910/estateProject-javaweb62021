package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;

public interface UserRepositoryCustom  {
    List<UserEntity> getStaffs(Long buildingId);
    boolean setChecked(Long buildingId,Long staffId);
    boolean setCheckedOfCustomer(Long customerId,Long staffId);
}
