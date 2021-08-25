package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingReponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enumm.BuildingTypesEnum;
import com.laptrinhjavaweb.enumm.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private BuildingRepository buildingRepository;
    @Override
    public List<BuildingReponseDTO> findAll() {
        List<BuildingEntity> entities = buildingRepository.findAll();
        List<BuildingReponseDTO> result = new ArrayList<>();
        for(BuildingEntity buildingEntity :entities){
            BuildingReponseDTO reponseDTO = buildingConverter.convertToDTO(buildingEntity);
            reponseDTO.setAddress(buildingEntity.getStreet()+"-"+buildingEntity.getWard()+"-"+DistrictsEnum.existDistrict(buildingEntity.getDistrictCode()));
            result.add(reponseDTO);
        }
        return result;
    }

    @Override
    @Transactional
    public void save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
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

}
