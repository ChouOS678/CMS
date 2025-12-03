package com.cms.controller;

import com.cms.common.Result;
import com.cms.entity.Transaction;
import com.cms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubFinanceController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}/transactions")
    public Result<List<Transaction>> getClubTransactions(@PathVariable Long id) {
        // Should check if user is club member
        return Result.success(transactionService.getClubTransactions(id));
    }
}
