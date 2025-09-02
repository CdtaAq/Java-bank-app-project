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

    //  Show all accounts
    @GetMapping
    public String listAccounts(Model model) {
        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "account";  // -> account.html / account.jsp
    }

    //  Show account creation form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("account", new Account());
        return "accountForm";  // -> accountForm.html
    }

    //  Save new account
    @PostMapping
    public String saveAccount(@ModelAttribute("account") Account account) {
        accountService.saveAccount(account);
        return "redirect:/accounts";
    }

    //  Show update form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        return "accountForm";  // Reuse form for editing
    }

    //  Update account
    @PostMapping("/update/{id}")
    public String updateAccount(@PathVariable Long id,
                                @ModelAttribute("account") Account updatedAccount) {
        accountService.updateAccount(id, updatedAccount);
        return "redirect:/accounts";
    }

    //  Delete account
    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }

    //  View single account details
    @GetMapping("/{id}")
    public String viewAccount(@PathVariable Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        return "accountDetails";  // -> accountDetails.html
    }
}
