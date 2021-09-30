package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AssignmentBuildingRepositoryCustom {
     void deleteStaff(AssignmentBuildingDTO assignmentBuildingDTO, Long id);
     void addStaff(AssignmentBuildingDTO search, Long id);
     List<UserEntity> staffList(Long buildingId);

}
