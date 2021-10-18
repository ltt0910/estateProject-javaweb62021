package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentCustomerService implements IAssignmentCustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void assignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findOne(assignmentCustomerDTO.getCustomerId());
        List<UserEntity> staff = new ArrayList<>();
        for (long item : assignmentCustomerDTO.getStaffs()) {
            staff.add(userRepository.findOne(item));

        }
        customerEntity.setUserEntities(staff);
        customerRepository.save(customerEntity);

    }
}
