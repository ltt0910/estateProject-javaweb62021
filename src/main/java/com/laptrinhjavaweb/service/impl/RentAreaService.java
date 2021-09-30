package com.laptrinhjavaweb.service.impl;
import com.laptrinhjavaweb.dto.reponse.RentAreaDTO;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.service.IRentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class RentAreaService implements IRentAreaService {
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Override
    public void addRentArea(RentAreaDTO rentAreaDTO) {
        Map<String,Object> rentAreas = new HashMap<String,Object>();
        rentAreas.put("value",rentAreaDTO.getValue());
        rentAreas.put("buildingId",rentAreaDTO.getBuildingId());
        rentAreaRepository.addRentArea(rentAreas);
    }

    @Override
    public void deleteRentArea(Long buildingId) {
        rentAreaRepository.deleteRentArea(buildingId);
    }
}
