package com.example.bank.service;

import com.example.bank.model.BankTransaction;
import java.util.List;

public interface BankTransactionService {
    List<BankTransaction> getAllTransactions();
    BankTransaction getTransactionById(Long id);
    BankTransaction saveTransaction(BankTransaction transaction);
    void deleteTransaction(Long id);
}
