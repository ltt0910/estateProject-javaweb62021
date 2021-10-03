package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AssignmentBuildingRepositoryCustom {
     void deleteStaff(AssignmentBuildingEntity assignmentBuildingEntity, Long id);
     void deleteAssignmentBuilding(Long buildingId);
     void addStaff(AssignmentBuildingEntity addStaff, Long id);
     List<UserEntity> staffList(Long buildingId);
}
