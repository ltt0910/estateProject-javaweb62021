package com.laptrinhjavaweb.repository.jdbc;

import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuildingJDBC {
	List<BuildingEntity> findAll(BuildingDTO searchBuilding);
}
