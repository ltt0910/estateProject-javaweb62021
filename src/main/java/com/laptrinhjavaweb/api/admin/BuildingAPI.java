package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
    @PostMapping
    public BuildingDTO addBuilding(BuildingDTO buildingDTO) {
        return buildingDTO;
    }
    @DeleteMapping
    public void deleteBuilding(Long [] buildingIds){

    }
}
