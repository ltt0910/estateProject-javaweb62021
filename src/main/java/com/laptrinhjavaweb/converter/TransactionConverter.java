package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.TransactionTypeDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;

    public TransactionTypeDTO convertToDTO(TransactionEntity entity) {
        TransactionTypeDTO dto = modelMapper.map(entity, TransactionTypeDTO.class);
        return dto;
    }

    public TransactionEntity convertToEntity(TransactionTypeDTO dto) {
        TransactionEntity entity = modelMapper.map(dto, TransactionEntity.class);
        return entity;
    }

    public TransactionDTO convertEntityToDTO(TransactionEntity entity) {
        TransactionDTO dto = modelMapper.map(entity, TransactionDTO.class);
        return dto;
    }
    public TransactionEntity convertDTOToEntity(TransactionDTO dto) {
        TransactionEntity entity = modelMapper.map(dto, TransactionEntity.class);
        return entity;
    }

}
