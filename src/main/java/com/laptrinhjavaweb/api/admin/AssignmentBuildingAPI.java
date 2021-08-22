package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assigntmentBuilding")
public class AssignmentBuildingAPI {
    @PostMapping
    public AssignmentBuildingDTO addAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        return assignmentBuildingDTO;
    }
}
