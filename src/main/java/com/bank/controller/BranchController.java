package com.bank.controller;

import com.bank.model.Branch;
import com.bank.service.BranchService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/branches")
public class BranchController {

    private final BranchService branchService;
    public BranchController(BranchService branchService) { this.branchService = branchService; }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("branches", branchService.getAllBranches());
        return "branchList";
    }

    // ADMIN-only (also protected by SecurityConfig)
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("branch", new Branch());
        return "branchForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Branch branch) {
        branchService.saveBranch(branch);
        return "redirect:/branches/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("branch", branchService.getBranchById(id));
        return "branchForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return "redirect:/branches/list";
    }
}
