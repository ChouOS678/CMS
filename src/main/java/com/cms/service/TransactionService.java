package com.cms.service;

import com.cms.entity.Transaction;
import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    List<Transaction> getClubTransactions(Long clubId);

    Transaction updateTransaction(Transaction transaction);

    void deleteTransaction(Long id);
}
