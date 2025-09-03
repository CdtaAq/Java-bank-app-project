package com.bank.controller;

import com.bank.model.Account;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Show all accounts
    @GetMapping
    public String listAccounts(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "account"; // maps to account.html / account.jsp
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("account", new Account());
        return "account_form"; // separate form view
    }

    // Save new account
    @PostMapping
    public String createAccount(@ModelAttribute("account") Account account) {
        accountService.createAccount(account);
        return "redirect:/accounts";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        return "account_form";
    }

    // Update account
    @PostMapping("/update/{id}")
    public String updateAccount(@PathVariable("id") Long id, @ModelAttribute("account") Account account) {
        accountService.updateAccount(id, account);
        return "redirect:/accounts";
    }

    // Delete account
    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }

    // View account details
    @GetMapping("/{id}")
    public String viewAccount(@PathVariable("id") Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        return "account_detail"; // detailed account page
    }
}
