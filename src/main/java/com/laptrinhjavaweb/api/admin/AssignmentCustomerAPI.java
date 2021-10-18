package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;
import com.laptrinhjavaweb.service.impl.AssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assigntmentCustomer")
public class AssignmentCustomerAPI {
    @Autowired
    private AssignmentCustomerService assignmentCustomerService;
    @PostMapping
    public void addAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        assignmentCustomerService.assignmentCustomer(assignmentCustomerDTO);
    }
}
