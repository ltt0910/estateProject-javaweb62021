package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> searchBuilding(Map<String,Object> params,List<String> buildingTypes);
    List<BuildingEntity> findBuildingAssignmentByStaff(Map<String,Object> params,List<String> buildingTypes,Long id);
    List<UserEntity> getStaffs(Long buildingId);

}
