package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.reponse.ReponseCustomerDTO;
import com.laptrinhjavaweb.dto.request.RequestCustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToDTO(CustomerEntity entity) {
        CustomerDTO dto = modelMapper.map(entity, CustomerDTO.class);
        return dto;
    }

    public CustomerEntity convertToEntity(CustomerDTO dto) {
        CustomerEntity entity = modelMapper.map(dto, CustomerEntity.class);
        return entity;
    }
    public ReponseCustomerDTO convertEntityToDTO(CustomerEntity entity) {
        ReponseCustomerDTO dto = modelMapper.map(entity, ReponseCustomerDTO.class);
        return dto;
    }

    public CustomerEntity convertDTOToEntity(RequestCustomerDTO dto) {
        CustomerEntity entity = modelMapper.map(dto, CustomerEntity.class);
        return entity;
    }
}
