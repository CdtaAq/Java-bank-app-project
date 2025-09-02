package com.example.bank.controller;

import com.example.bank.model.Account;
import com.example.bank.service.AccountService;
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

    /**
     * Show all accounts
     */
    @GetMapping
    public String listAccounts(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "account-list";  // JSP/HTML page
    }

    /**
     * Show account creation form
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("account", new Account());
        return "account-form";  // JSP/HTML page
    }

    /**
     * Handle account creation
     */
    @PostMapping("/save")
    public String saveAccount(@ModelAttribute("account") Account account) {
        accountService.saveAccount(account);
        return "redirect:/accounts";
    }

    /**
     * Show edit form
     */
    @GetMapping("/edit/{id}")
    public String editAccount(@PathVariable("id") Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        return "account-form";
    }

    /**
     * Delete an account
     */
    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }

    /**
     * Show account details
     */
    @GetMapping("/{id}")
    public String viewAccount(@PathVariable("id") Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        return "account-details";  // JSP/HTML page
    }
}
