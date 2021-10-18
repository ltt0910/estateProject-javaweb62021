package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;

import java.util.List;

public interface ICustomerService {
    void save(CustomerDTO customerDTO);
    void delete(long id);
    CustomerDTO findOne(Long id);
    List<StaffReponse> getStaff(Long id);
    List<CustomerDTO> searchCustomer(CustomerDTO customerDTO);

}
