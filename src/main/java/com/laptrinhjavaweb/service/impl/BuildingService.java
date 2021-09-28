package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
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
    @Override
    public List<BuildingDTO> searchBuilding(Map<String,Object> params,String[] types) {
        List<BuildingEntity> entities = new ArrayList<>();
        if(SecurityUtils.getAuthorities().contains("ROLE_staff")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            entities= buildingRepository.findBuildingAssignmentByStaff(params,types,staffId);
        }
        else{
            entities = buildingRepository.searchBuilding(params,types);
        }
        List<BuildingDTO> result = new ArrayList<>();
        for(BuildingEntity buildingEntity :entities){
            BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
            buildingDTO.setAddress(buildingEntity.getStreet()+"-"+buildingEntity.getWard()+"-"+DistrictsEnum.existDistrict(buildingEntity.getDistrict()));
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
        buildingEntity.setTypes(types);
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void delete(Long id) {
        buildingRepository.delete(id);
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
        buildingDTO = buildingConverter.convertToDTO(buildingEntity);
        return buildingDTO;
    }

    @Override
    public List<StaffReponse> getStaff(Long buildingId) {
        List<StaffReponse> result = new ArrayList<>();
        List<UserEntity> userEntities = buildingRepository.getStaffs(buildingId);
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
