package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingReponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public BuildingEntity convertToEntity(BuildingDTO dto) {
        BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);
        return entity;
    }
    public BuildingReponseDTO convertToDTO(BuildingEntity entity) {
        BuildingReponseDTO dto = modelMapper.map(entity, BuildingReponseDTO.class);
        return dto;
    }
}

