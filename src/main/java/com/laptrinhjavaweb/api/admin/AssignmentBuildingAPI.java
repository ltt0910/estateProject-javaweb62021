package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.impl.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assigntmentBuilding")
public class AssignmentBuildingAPI {
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;
    @PostMapping
    public void addAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        assignmentBuildingService.assignmentBuilding(assignmentBuildingDTO);
    }


}
