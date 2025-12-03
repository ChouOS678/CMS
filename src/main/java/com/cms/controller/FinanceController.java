package com.cms.controller;

import com.cms.common.Result;
import com.cms.entity.Transaction;
import com.cms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finances")
public class FinanceController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public Result<Transaction> createTransaction(@RequestBody Transaction transaction) {
        // Should check for club admin role
        return Result.success(transactionService.createTransaction(transaction));
    }

    @PutMapping("/transactions/{id}")
    public Result<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        // Should check for club admin role
        transaction.setId(id);
        return Result.success(transactionService.updateTransaction(transaction));
    }

    @DeleteMapping("/transactions/{id}")
    public Result<Void> deleteTransaction(@PathVariable Long id) {
        // Should check for club admin role
        transactionService.deleteTransaction(id);
        return Result.success();
    }
}
