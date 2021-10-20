package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.reponse.ReponseCustomerDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.dto.request.RequestCustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public interface ICustomerService {
    void save(CustomerDTO customerDTO);
    void delete(long id);
    CustomerDTO findOne(Long id);
    List<StaffReponse> getStaff(Long id);
    List<ReponseCustomerDTO> searchCustomer(RequestCustomerDTO customerDTO);
    void assignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO);

}
