package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.jdbc.IDistrictJDBC;
import com.laptrinhjavaweb.repository.jdbc.impl.DistrictJDBC;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.DistrictDTO;

@Component
public class BuildingConverter {
	
	@Autowired
	public static DistrictDTO districtDTO = new DistrictDTO();
	private ModelMapper modelMapper = new ModelMapper();
	private IDistrictJDBC districtJDBC = new DistrictJDBC();
	public BuildingDTO convertToDTO(BuildingEntity entity) {
		BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
		dto.setAddress(entity.getStreet()+" , "+entity.getWard()+" , "+districtDTO.getName());
		return dto;
	}
	
	public BuildingEntity convertToEntity(BuildingDTO dto) {
		BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);
		return entity;
	}

}
