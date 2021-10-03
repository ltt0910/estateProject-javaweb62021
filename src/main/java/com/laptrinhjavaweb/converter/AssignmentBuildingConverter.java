package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignmentBuildingConverter {
    @Autowired
    private ModelMapper modelMapper;
    public AssignmentBuildingDTO convertToDTO(AssignmentBuildingEntity entity) {
        AssignmentBuildingDTO dto = modelMapper.map(entity, AssignmentBuildingDTO.class);
        return dto;
    }
    public AssignmentBuildingEntity convertToEntity(AssignmentBuildingDTO dto) {
        AssignmentBuildingEntity entity = modelMapper.map(dto, AssignmentBuildingEntity.class);
        return entity;
    }

}
