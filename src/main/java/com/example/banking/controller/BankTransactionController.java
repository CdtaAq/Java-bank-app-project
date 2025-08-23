package com.example.banking.controller;

import com.example.banking.entity.BankTransaction;
import com.example.banking.repository.BankTransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class BankTransactionController {

    private final BankTransactionRepository bankTransactionRepository;

    public BankTransactionController(BankTransactionRepository bankTransactionRepository) {
        this.bankTransactionRepository = bankTransactionRepository;
    }

    @GetMapping
    public String listTransactions(Model model) {
        List<BankTransaction> transactions = bankTransactionRepository.findAll();
        model.addAttribute("transactions", transactions);
        return "bankTransactions";
    }

    @PostMapping
    public String saveTransaction(@ModelAttribute BankTransaction bankTransaction) {
        bankTransactionRepository.save(bankTransaction);
        return "redirect:/transactions";
    }
}
