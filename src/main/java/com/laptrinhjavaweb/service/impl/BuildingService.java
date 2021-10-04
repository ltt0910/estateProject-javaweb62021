package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.RentAreaDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
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
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Override
    public List<BuildingDTO> searchBuilding(BuildingDTO dto) {
        List<BuildingEntity> entities = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = buildingConverter.convertToBuildingSearchBuilder(dto);
        if(SecurityUtils.getAuthorities().contains("ROLE_staff")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            entities= buildingRepository.findBuildingAssignmentByStaff(buildingSearchBuilder,staffId);
        }
        else{
            entities = buildingRepository.searchBuilding(buildingSearchBuilder);
        }

        List<BuildingDTO> result = new ArrayList<>();
        for(BuildingEntity buildingEntity :entities){
            List<String> rentAreaStrings = new ArrayList<>();
            List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
            for(RentAreaEntity item:rentAreaEntities){
                if(item.getValue()!=null){
                    rentAreaStrings.add(item.getValue().toString());
                }
            }
            Object[] rentAreas = rentAreaStrings.toArray();
            String rentArea = Arrays.toString(rentAreas).replace("[","");
            rentArea = rentArea.replace("]","");
            rentArea = rentArea.replace(" ","");
            BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
            buildingDTO.setRentArea(rentArea);
            if(buildingEntity.getDistrict().equals("-1")) {
                buildingDTO.setAddress(buildingEntity.getStreet()+"-"+buildingEntity.getWard());
            }
            else{
                buildingDTO.setAddress(buildingEntity.getStreet()+"-"+buildingEntity.getWard()+"-"+DistrictsEnum.existDistrict(buildingEntity.getDistrict()));
            }

            result.add(buildingDTO);
        }
        return result;
    }

    @Override
    @Transactional
    public void save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        String[] buildingTypes = buildingDTO.getBuildingTypes();
        String types = Arrays.toString(buildingTypes).replace("[","");
        types = types.replace("]","");
        types = types.replace(" ","");
        buildingEntity.setTypes(types);
        String[] rentAreas = buildingDTO.getRentArea().split(",");
        List<RentAreaEntity> areaEntities = new ArrayList<>();
        for (String item: rentAreas) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(Integer.parseInt(item));
            areaEntities.add(rentAreaEntity);
        }
        buildingEntity.setRentAreas(areaEntities);
        buildingRepository.save(buildingEntity);

        if(buildingDTO.getId()==null){

        }
        else{
            rentAreaService.deleteRentArea(buildingDTO.getId());
            buildingRepository.save(buildingEntity);
        }
        }

    @Override
    public void delete(Long id) {
        try{
            assignmentBuildingRepository.deleteAssignmentBuilding(id);
            buildingRepository.delete(id);
        }catch (Exception e){
            rentAreaService.deleteRentArea(id);
            buildingRepository.delete(id);
        }
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
        BuildingDTO buildingDTO = new BuildingDTO();
        BuildingEntity buildingEntity = buildingRepository.findOne(id);
        String[] buildingTypes = buildingEntity.getTypes().split(",");
        List<String> rentAreaStrings = new ArrayList<>();
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
        for(RentAreaEntity item:rentAreaEntities){
            rentAreaStrings.add(item.getValue().toString());
        }
        Object[] rentAreas = rentAreaStrings.toArray();
        String rentArea = Arrays.toString(rentAreas).replace("[","");
        rentArea = rentArea.replace("]","");
        rentArea =rentArea.replace(" ","");
        buildingDTO = buildingConverter.convertToDTO(buildingEntity);
        buildingDTO.setBuildingTypes(buildingTypes);
        buildingDTO.setRentArea(rentArea);
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

}
