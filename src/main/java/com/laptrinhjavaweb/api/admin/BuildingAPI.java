package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingReponseDTO;
import com.laptrinhjavaweb.dto.reponse.ReponseDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponseDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public BuildingDTO deleteBuildingById(@RequestBody long[] ids) {
        for (long item:ids){
            buildingService.delete(item);
        }
        return new BuildingDTO();
    }
    @GetMapping("/{buildingid}/staffs")
    public ReponseDTO loadStaff() {
        ReponseDTO result = new ReponseDTO();
        List<StaffReponseDTO> staffs = new ArrayList<>();
        StaffReponseDTO staffReponseDTO1 = new StaffReponseDTO();
        staffReponseDTO1.setId(1L);
        staffReponseDTO1.setFullname("nguyen van b");
        staffReponseDTO1.setChecked("checked");
        staffs.add(staffReponseDTO1);
        StaffReponseDTO staffReponseDTO2 = new StaffReponseDTO();
        staffReponseDTO2.setId(2L);
        staffReponseDTO2.setFullname("nguyen van c");
        staffReponseDTO2.setChecked("checked");
        staffs.add(staffReponseDTO2);
        StaffReponseDTO staffReponseDTO3 = new StaffReponseDTO();
        staffReponseDTO3.setId(3L);
        staffReponseDTO3.setFullname("nguyen van d");
        staffReponseDTO3.setChecked("");
        staffs.add(staffReponseDTO3);
        result.setMessage("success");
        result.setData(staffs);
        return result;

    }
}
