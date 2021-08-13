package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.DistrictEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.DistrictConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.DistrictDTO;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
//import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.IBuildingJDBC;
import com.laptrinhjavaweb.repository.jdbc.IDistrictJDBC;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingJDBC;
import com.laptrinhjavaweb.repository.jdbc.impl.DistrictJDBC;
import com.laptrinhjavaweb.service.IBuildingService;


public class BuildingService implements IBuildingService {
	
	
	private BuildingConverter buildingConverter = new BuildingConverter();
	private DistrictConverter districtConverter = new DistrictConverter();

	@Autowired
	private IBuildingJDBC buildingJdbc = new BuildingJDBC();
	private IDistrictJDBC districtJdbc = new DistrictJDBC();
	@Transactional
	@Override
	public List<BuildingDTO> findListBuilding(BuildingSearchBuilder searchBuilding) {
		HashMap<String, Object> mapBuilding = new HashMap<String, Object>();
		
		mapBuilding.put("name", searchBuilding.getName());
		mapBuilding.put("numberOfBasement",searchBuilding.getNumberOfBasement());
		mapBuilding.put("areaRentFrom", searchBuilding.getAreaRentFrom());
		mapBuilding.put("areaRentTo",searchBuilding.getAreaRentTo());
		mapBuilding.put("buildingTypes", searchBuilding.getBuildingTypes());
		mapBuilding.put("costRentFrom", searchBuilding.getCostRentFrom());
		mapBuilding.put("costRentTo", searchBuilding.getCostRentTo());
		mapBuilding.put("ward",searchBuilding.getWard());
		mapBuilding.put("street", searchBuilding.getStreet());
		mapBuilding.put("managerName", searchBuilding.getManagerName());
		mapBuilding.put("managerPhone",searchBuilding.getManagerPhone());
		mapBuilding.put("staffId", searchBuilding.getStaffId());
		List<BuildingDTO> buildingList = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingJdbc.findListBuilding(mapBuilding);
		
		for(BuildingEntity buildingEntity:buildingEntities) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO = buildingConverter.convertToDTO(buildingEntity);
			BuildingConverter.districtDTO = districtConverter.entityToDto(districtJdbc.getDistrictById(buildingEntity.getDistrictId()));
			buildingList.add(buildingDTO);
		}
		return buildingList;
	}



	
	
}
