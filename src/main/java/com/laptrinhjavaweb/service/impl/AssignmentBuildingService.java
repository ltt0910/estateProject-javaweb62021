package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UserConverter converter;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity building = buildingRepository.findOne(assignmentBuildingDTO.getBuildingId());
        List<UserEntity> staff = new ArrayList<>();
        for (long item : assignmentBuildingDTO.getStaffs()) {
            staff.add(userRepository.findOne(item));

        }
        building.setUserEntities(staff);
        buildingRepository.save(building);
    }

    @Override
    public List<StaffReponse> staffList(Long buildingId) {
        List<UserEntity> entities = assignmentBuildingRepository.staffList(buildingId);
        List<StaffReponse> result = new ArrayList<>();
        for (UserEntity item:entities){
           StaffReponse staffReponse = converter.convertToStaffReponse(item);
          result.add(staffReponse);
        }
        return result;
    }
}

