package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        customerRepository.save(customerEntity);
    }

    @Override
    public void delete(long id) {
        customerRepository.delete(id);
    }

    @Override
    public CustomerDTO findOne(Long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO = customerConverter.convertToDTO(customerEntity);
        return customerDTO;
    }

    @Override
    public List<StaffReponse> getStaff(Long id) {
        List<StaffReponse> result = new ArrayList<>();
        List<UserEntity> userEntities = customerRepository.getStaffs();
        for(UserEntity item:userEntities){
            StaffReponse staffReponse = userConverter.convertToStaffReponse(item);
            if(userRepository.setCheckedOfCustomer(id,item.getId())){
                staffReponse.setChecked("checked");
            }
            result.add(staffReponse);
        }
        return result;
    }

    @Override
    public List<CustomerDTO> searchCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity = customerConverter.convertToEntity(customerDTO);
        List<CustomerEntity> customerEntities = new ArrayList<>();
        if(SecurityUtils.getAuthorities().contains("ROLE_manager")){
            customerEntities = customerRepository.searchCustomer(customerEntity,customerDTO.getStaffId());
        }
        else {
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerEntities = customerRepository.searchCustomer(customerEntity,staffId);
        }
        List<CustomerDTO> result = new ArrayList<>();
        for (CustomerEntity item:customerEntities) {
            CustomerDTO customerDTO1 = new CustomerDTO();
            customerDTO1 = customerConverter.convertToDTO(item);
            result.add(customerDTO1);
        }
        return result;
    }
}
