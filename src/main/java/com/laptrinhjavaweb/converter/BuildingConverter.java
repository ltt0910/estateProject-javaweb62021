package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;
    public BuildingDTO convertToDTO(BuildingEntity entity) {
        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
        dto.setRentArea(convertRentAreaListToString(entity));
        dto.setAddress(convertToAddress(entity));
        return dto;
    }
    public BuildingDTO convertToDTO1(BuildingEntity entity) {
        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
        dto.setRentArea(convertRentAreaListToString(entity));
        dto.setAddress(convertToAddress(entity));
        dto.setBuildingTypes(entity.getTypes().split(","));
        return dto;
    }
    private String convertRentAreaListToString(BuildingEntity buildingEntity){
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
        List<String> rentAreaStrings = new ArrayList<>();
        for(RentAreaEntity item:rentAreaEntities){
            if(item.getValue()!=null){
                rentAreaStrings.add(item.getValue().toString());
            }
        }
        Object[] rentAreas = rentAreaStrings.toArray();
        String rentArea = Arrays.toString(rentAreas).replace("[","");
        rentArea = rentArea.replace("]","");
        rentArea = rentArea.replace(" ","");
        return rentArea;
    }

    private String convertToAddress(BuildingEntity buildingEntity){
        String address ="";
        if(buildingEntity.getDistrict().equals("-1")) {
            address = buildingEntity.getStreet()+"-"+buildingEntity.getWard();
        }
        else{
            address = buildingEntity.getStreet()+"-"+buildingEntity.getWard()+"-"+ DistrictsEnum.existDistrict(buildingEntity.getDistrict());
        }
        return address;
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

