package com.example.bank.service.impl;

import com.example.bank.model.BankTransaction;
import com.example.bank.repository.BankTransactionRepository;
import com.example.bank.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

    @Autowired
    private BankTransactionRepository transactionRepository;

    @Override
    public List<BankTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public BankTransaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    @Override
    public BankTransaction saveTransaction(BankTransaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
