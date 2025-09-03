package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // 1. List all accounts
    @GetMapping
    public String listAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts_list";
    }

    // 2. Show form to create new account
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("account", new Account());
        return "account_form";
    }

    // 3. Save new account
    @PostMapping("/save")
    public String saveAccount(@ModelAttribute("account") Account account) {
        accountService.saveAccount(account);
        return "redirect:/accounts";
    }

    // 4. Show form to edit existing account
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        return "account_form";
    }

    // 5. Update existing account
    @PostMapping("/update")
    public String updateAccount(@ModelAttribute("account") Account account) {
        accountService.updateAccount(account);
        return "redirect:/accounts";
    }

    // 6. View account details
    @GetMapping("/{id}")
    public String viewAccount(@PathVariable("id") Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        return "account_detail";
    }

    // 7. Delete account
    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }
}
