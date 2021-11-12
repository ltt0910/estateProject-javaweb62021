package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Override
    public List<BuildingDTO> searchBuilding(BuildingDTO dto) {
        List<BuildingEntity> entities = new ArrayList<>();
        if(SecurityUtils.getAuthorities().contains("ROLE_staff")) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            dto.setStaffId(staffId);
        }
        BuildingSearchBuilder buildingSearchBuilder = buildingConverter.convertToBuildingSearchBuilder(dto);
        entities = buildingRepository.searchBuilding(buildingSearchBuilder);
        List<BuildingDTO> result = new ArrayList<>();
        for(BuildingEntity buildingEntity :entities){
            BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
            result.add(buildingDTO);
        }
        return result;
    }

    @Override
    @Transactional
    public void save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        buildingEntity.setRentAreas(buildingRentArea(buildingDTO));
        buildingEntity.setTypes(String.join(",",buildingDTO.getBuildingTypes()));
        if(buildingDTO.getId()!=null){
            rentAreaRepository.deleteRentArea(buildingDTO.getId());
        }
        buildingRepository.save(buildingEntity);
        }

    public List<RentAreaEntity> buildingRentArea(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        List<RentAreaEntity> areaEntities = new ArrayList<>();
        String[] rentAreas = buildingDTO.getRentArea().split(",");
        for (String item: rentAreas) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(Integer.parseInt(item));
            areaEntities.add(rentAreaEntity);
        }
        return areaEntities;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        buildingRepository.deleteBuildingEntityById(id);
    }

    @Override
    public Map<String, String> districtName() {
        Map<String,String> result = new HashMap<>();
        for (DistrictsEnum item:DistrictsEnum.values()){
            result.put(item.toString(),item.getDistrictValue());
        }
        return result;
    }

    @Override
    public Map<String, String> buildingTypes() {
        Map<String,String> result = new HashMap<>();
        for (BuildingTypesEnum item:BuildingTypesEnum.values()){
            result.put(item.toString(),item.getBuildingTypes());
        }
        return result;
    }
    public BuildingDTO findById(Long id){
        BuildingEntity buildingEntity = buildingRepository.findOne(id);
        List<String> rentAreaStrings = new ArrayList<>();
        BuildingDTO buildingDTO = buildingConverter.convertToDTO1(buildingEntity);
        return buildingDTO;
    }

    @Override
    public List<StaffReponse> getStaff(Long buildingId) {
        List<StaffReponse> result = new ArrayList<>();
        List<UserEntity> userEntities = buildingRepository.getStaffs();
        for(UserEntity item:userEntities){
            StaffReponse staffReponse = userConverter.convertToStaffReponse(item);
            if(userRepository.setChecked(buildingId,item.getId())){
                staffReponse.setChecked("checked");
            }
            result.add(staffReponse);
        }
        return result;
    }

    @Override
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
           StaffReponse staffReponse = userConverter.convertToStaffReponse(item);
          result.add(staffReponse);
        }
        return result;
    }

}
