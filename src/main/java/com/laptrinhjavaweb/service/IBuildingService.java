package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingDTO> searchBuilding(BuildingDTO buildingDTO);
    void save(BuildingDTO buildingDTO);
    Map<String,String> districtName();
    Map<String,String> buildingTypes();
    void delete(Long id);
    BuildingDTO findById(Long id);
    List<StaffReponse> getStaff(Long id);
}
