package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.DistrictDTO;
import com.laptrinhjavaweb.entity.DistrictEntity;

public class DistrictConverter {
	private ModelMapper modelMapper = new ModelMapper();

    public DistrictDTO entityToDto(DistrictEntity entity){
        DistrictDTO result = modelMapper.map(entity, DistrictDTO.class);
        return result;
    }
}
