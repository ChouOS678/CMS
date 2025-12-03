package com.cms.service.impl;

import com.cms.entity.Transaction;
import com.cms.mapper.TransactionMapper;
import com.cms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setCreateTime(new Date());
        transactionMapper.insert(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> getClubTransactions(Long clubId) {
        return transactionMapper.selectByClubId(clubId);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        transactionMapper.update(transaction);
        // In a real app, we would fetch the updated record
        return transaction;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionMapper.deleteById(id);
    }
}
