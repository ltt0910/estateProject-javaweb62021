package com.laptrinhjavaweb.repository.jdbc;

import java.util.HashMap;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuildingJDBC {
	List<BuildingEntity> findListBuilding(HashMap<String, Object> mapBuilding);
	
}
