package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.RentAreaEntity;

import java.util.Map;

public interface RentAreaRepositoryCustom {
    void addRentArea(RentAreaEntity rentAreaEntity);
    void deleteRentArea(Long buildingId);
}
