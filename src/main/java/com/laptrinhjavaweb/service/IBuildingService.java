package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingReponseDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingReponseDTO> findAll();
    void save(BuildingDTO buildingDTO);
    void delete(Long ids);
    Map<String,String> districtName();
    Map<String,String> buildingTypes();
}
