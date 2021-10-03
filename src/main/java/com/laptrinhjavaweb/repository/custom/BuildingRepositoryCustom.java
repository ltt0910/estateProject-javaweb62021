package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder);
    List<BuildingEntity> findBuildingAssignmentByStaff(BuildingSearchBuilder buildingSearchBuilder,Long staffId);
    List<UserEntity> getStaffs();

}
