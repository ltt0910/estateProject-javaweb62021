package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.entity.BuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
//import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.IBuildingJDBC;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingJDBC;
import com.laptrinhjavaweb.service.IBuildingService;


public class BuildingService implements IBuildingService {

	private BuildingConverter buildingConverter = new BuildingConverter();
	

	@Autowired
	private IBuildingJDBC buildingJdbc = new BuildingJDBC();
	

	@Transactional
	@Override
	public List<BuildingDTO> findListBuilding(BuildingDTO searchBuilding) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", searchBuilding.getName());
		map.put("numberofbasement",searchBuilding.getNumberOfBasement());
		map.put("district", searchBuilding.getDistrict());
		map.put("areaRentFrom", searchBuilding.getAreaRentFrom());
		map.put("areaRentTo",searchBuilding.getAreaRentTo());
		map.put("buildingTypes", searchBuilding.getBuildingTypes());
		map.put("costRentFrom", searchBuilding.getCostRentFrom());
		map.put("costRentTo", searchBuilding.getCostRentTo());
		map.put("ward",searchBuilding.getWard());
		map.put("street", searchBuilding.getStreet());
		map.put("managerName", searchBuilding.getManagerName());
		map.put("managerPhone",searchBuilding.getManagerPhone());
		map.put("staffId", searchBuilding.getStaffId());
		
		List<BuildingDTO> result = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingJdbc.findAll(map);
		for(BuildingEntity item:buildingEntities) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO = buildingConverter.convertToDTO(item);
			result.add(buildingDTO);
		}
		return result;
	}
	
}
