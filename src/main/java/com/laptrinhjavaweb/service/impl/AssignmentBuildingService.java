package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {

    @Autowired
    private UserService userService;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UserConverter converter;
    public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        List<Long> newStaffId = assignmentBuildingDTO.getStaffs();
        List<Long> oldStaffId = new ArrayList<>();

        List<StaffReponse> oldStaffList = staffList(assignmentBuildingDTO.getBuildingId());
        for(StaffReponse staffReponse:oldStaffList){
            oldStaffId.add(staffReponse.getId());
        }
        List<Long> staffDelete = new ArrayList<>();
        List<Long> staffUpdate = new ArrayList<>();

        for(Long id:oldStaffId) {
            if(!newStaffId.contains(id)) {
                staffDelete.add(id);
            }
        }
        for(Long id:newStaffId) {
            if(!oldStaffId.contains(id)) {
                staffUpdate.add(id);
            }
        }
        if(staffUpdate.size()>0) {
            for(Long id : staffUpdate) {
                assignmentBuildingRepository.addStaff(assignmentBuildingDTO, id);
            }
        }

        if(staffDelete.size()>0) {
            for(Long id : staffDelete) {
                assignmentBuildingRepository.deleteStaff(assignmentBuildingDTO, id);

            }
        }

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
