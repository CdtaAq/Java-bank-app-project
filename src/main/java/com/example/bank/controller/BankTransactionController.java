package com.example.bank.controller;

import com.example.bank.model.BankTransaction;
import com.example.bank.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class BankTransactionController {

    @Autowired
    private BankTransactionService transactionService;

    // List all transactions
    @GetMapping
    public String listTransactions(Model model) {
        List<BankTransaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transaction"; // transaction.html / transaction.jsp
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("transaction", new BankTransaction());
        return "transactionForm";
    }

    // Save transaction
    @PostMapping("/save")
    public String saveTransaction(@ModelAttribute("transaction") BankTransaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions";
    }

    // Edit transaction
    @GetMapping("/edit/{id}")
    public String editTransaction(@PathVariable Long id, Model model) {
        BankTransaction transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "transactionForm";
    }

    // Delete transaction
    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }
}
