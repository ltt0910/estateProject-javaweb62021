package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.TransactionTypeDTO;
import com.laptrinhjavaweb.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "transactionAPIOfAdmin")
@RequestMapping("/api/transaction")
public class TransactionAPI {
    @Autowired
    private TransactionService transactionService;
    @PostMapping
    public TransactionTypeDTO createBuilding(@RequestBody TransactionTypeDTO newTransaction) {
        transactionService.save(newTransaction);
        return newTransaction;
    }
}
