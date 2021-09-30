package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;

import java.util.List;

public interface IAssignmentBuildingService {
    void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
    List<StaffReponse> staffList(Long buildingId);

}
