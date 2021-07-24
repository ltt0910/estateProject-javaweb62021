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

//	@Autowired
	private BuildingConverter buildingConverter = new BuildingConverter();
	

	@Autowired
	private IBuildingJDBC buildingJdbc = new BuildingJDBC();
	

	@Transactional
	@Override
	public List<BuildingDTO> findAll(BuildingDTO searchBuilding) {
		List<BuildingDTO> result = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingJdbc.findAll(searchBuilding);
		for(BuildingEntity item:buildingEntities) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO = buildingConverter.convertToDTO(item);
			result.add(buildingDTO);
		}
		return result;
	}
	
}
