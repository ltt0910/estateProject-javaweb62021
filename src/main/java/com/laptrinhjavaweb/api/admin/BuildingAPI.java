package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;
    @PostMapping
    public BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding) {
        buildingService.save(newBuilding);
        return newBuilding;
    }

    @DeleteMapping
    public void deleteBuildingById(@RequestBody long[] ids) {
        for(long id:ids){
            buildingService.delete(id);
        }
    }
    @GetMapping("/{id}")
    public BuildingDTO findById(@PathVariable long id){
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO = buildingService.findById(id);
        return buildingDTO;
    }
    @PutMapping("/{id}")
    public void updateBuilding(@RequestBody BuildingDTO editBuilding){
        buildingService.save(editBuilding);
    }

    @GetMapping("/{id}/staffs")
    List<StaffReponse> getStaff(@PathVariable Long id){
        List<StaffReponse> result = buildingService.getStaff(id);
        return result;
    }

    @PostMapping("/assignment")
    public void addAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        buildingService.assignmentBuilding(assignmentBuildingDTO);
    }

}
