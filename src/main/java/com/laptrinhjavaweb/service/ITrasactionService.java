package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.TransactionTypeDTO;

import java.util.List;

public interface ITrasactionService {
    List<TransactionTypeDTO> getTransactionByCustomer(Long customerId);
    TransactionTypeDTO save(TransactionTypeDTO newTransaction);
}
