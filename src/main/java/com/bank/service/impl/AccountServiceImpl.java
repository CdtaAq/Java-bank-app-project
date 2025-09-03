package com.bank.service.impl;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Account existing = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        existing.setAccountType(account.getAccountType());
        existing.setBalance(account.getBalance());
        existing.setBranch(account.getBranch());
        existing.setOwner(account.getOwner());
        return accountRepository.save(existing);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
