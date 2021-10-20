package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.TransactionTypeDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enums.TransactionEnum;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITrasactionService;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService implements ITrasactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<TransactionTypeDTO> getTransactionByCustomer(Long customerId) {
        List<TransactionTypeDTO> result = new ArrayList<>();
        List<TransactionEntity> transactionEntities = new ArrayList<>();
            transactionEntities = transactionRepository.getTransactionByCustomer(customerId);
        for (TransactionEntity item:transactionEntities) {
            TransactionTypeDTO transactionDTO = new TransactionTypeDTO();
            transactionDTO = transactionConverter.convertToDTO(item);
            result.add(transactionDTO);
        }
        return result;
    }

    @Override
    @Transactional
    public TransactionTypeDTO save(TransactionTypeDTO newTransaction) {
        if(!StringUtils.isNullOrEmty(newTransaction.getNote())){
            TransactionEntity transactionEntity  = new TransactionEntity();
            transactionEntity.setCustomer(customerRepository.findOne(newTransaction.getCustomerId()));
            transactionEntity = transactionConverter.convertToEntity(newTransaction);
            transactionRepository.save(transactionEntity);
            return newTransaction;
        }
        return null;
    }

    @Override
    public TransactionTypeDTO getOne(Long customerId) {
        TransactionTypeDTO result = new TransactionTypeDTO();
        TransactionEntity transactionEntity = transactionRepository.getOne(customerId);
        result = transactionConverter.convertToDTO(transactionEntity);
        return result;
    }

    public Map<String,String> getTransaction() {
        Map<String,String> result = new HashMap<>();
        for (TransactionEnum item: TransactionEnum.values()) {
            result.put(item.toString(),item.getTransactionType());
        }
        return result;
    }
}
