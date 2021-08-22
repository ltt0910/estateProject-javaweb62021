package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingDTO> findAll();
    void save(BuildingDTO buildingDTO);
    void delete(Long id);
    Map<String,String> districtName();
    Map<String,String> buildingTypes();

}
