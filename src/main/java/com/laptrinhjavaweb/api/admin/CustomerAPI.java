package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO newCustomer) {
        customerService.save(newCustomer);
        return newCustomer;
    }

    @PostMapping("/assignment")
    public void addAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        customerService.assignmentCustomer(assignmentCustomerDTO);
    }

    @DeleteMapping
    public void deleteBuildingById(@RequestBody long[] ids) {
        for(long id:ids){
            customerService.delete(id);
        }
    }
    @GetMapping("/{id}/staffs")
    List<StaffReponse> getStaff(@PathVariable Long id){
        List<StaffReponse> result = new ArrayList<>();
        result = customerService.getStaff(id);
        return result;
    }
}
