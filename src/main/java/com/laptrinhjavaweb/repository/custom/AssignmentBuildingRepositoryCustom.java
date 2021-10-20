package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;

public interface AssignmentBuildingRepositoryCustom {
     void deleteAssignmentBuilding(Long buildingId);
     List<UserEntity> staffList(Long buildingId);
}
