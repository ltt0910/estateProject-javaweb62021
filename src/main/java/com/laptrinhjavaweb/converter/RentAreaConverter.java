package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.RentAreaDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentAreaConverter {
    @Autowired
    private ModelMapper modelMapper ;
    public RentAreaDTO convertToDTO(RentAreaEntity entity) {
        RentAreaDTO dto = modelMapper.map(entity, RentAreaDTO.class);
        return dto;
    }
    public RentAreaEntity convertToEntity(RentAreaDTO dto) {
        RentAreaEntity entity = modelMapper.map(dto, RentAreaEntity.class);
        return entity;
    }

}
