package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;

public interface IBuildingService {
	List<BuildingDTO> findListBuilding(BuildingSearchBuilder searchBuilding);
//	String getDistrictById(Long id);
}
