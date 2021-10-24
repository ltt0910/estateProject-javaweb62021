package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.AssignmentCustomerDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.reponse.ReponseCustomerDTO;
import com.laptrinhjavaweb.dto.reponse.StaffReponse;
import com.laptrinhjavaweb.dto.request.RequestCustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public void save(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        customerRepository.save(customerEntity);
    }

    @Override
    @Transactional
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
    public List<ReponseCustomerDTO> searchCustomer(RequestCustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity = customerConverter.convertDTOToEntity(customerDTO);
        List<CustomerEntity> customerEntities = new ArrayList<>();
        if(SecurityUtils.getAuthorities().contains("ROLE_manager")){
            customerEntities = customerRepository.searchCustomer(customerEntity,customerDTO.getStaffId());
        }
        else {
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerEntities = customerRepository.searchCustomer(customerEntity,staffId);
        }
        List<ReponseCustomerDTO> result = new ArrayList<>();
        for (CustomerEntity item:customerEntities) {
            ReponseCustomerDTO customerDTO1 = new ReponseCustomerDTO();
            customerDTO1 = customerConverter.convertEntityToDTO(item);
            result.add(customerDTO1);
        }
        return result;
    }

    @Override
    @Transactional
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
