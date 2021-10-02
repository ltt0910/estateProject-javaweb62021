package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();
    public BuildingDTO convertToDTO(BuildingEntity entity) {
        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
        return dto;
    }

    public BuildingEntity convertToEntity(BuildingDTO dto) {
        BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);
        return entity;
    }
    public BuildingSearchBuilder convertToBuildingSearchBuilder(BuildingDTO buildingDTO){
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder().setName(buildingDTO.getName())
                .setNumberOfBasement(buildingDTO.getNumberOfBasement()).setBuildingTypes(buildingDTO.getBuildingTypes())
                .setDistrict(buildingDTO.getDistrict()).setFloorArea(buildingDTO.getFloorArea()).setDirection(buildingDTO.getDirection())
                .setLevel(buildingDTO.getLevel()).setManagerName(buildingDTO.getManagerName()).setManagerPhone(buildingDTO.getManagerPhone())
                .setRentAreaFrom(buildingDTO.getAreaRentFrom()).setRentAreaTo(buildingDTO.getAreaRentTo()).setRentCostFrom(buildingDTO.getCostRentFrom())
                .setRentCostTo(buildingDTO.getCostRentTo()).setStreet(buildingDTO.getStreet()).setWard(buildingDTO.getWard()).setEmployee(buildingDTO.getStaffId()).build();
        return result;
    }
}

