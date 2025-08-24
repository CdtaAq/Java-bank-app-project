package com.bank.controller;

import com.bank.model.Branch;
import com.bank.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    // Show branch form
    @GetMapping("/form")
    public String showBranchForm(Model model) {
        model.addAttribute("branch", new Branch());
        return "branchForm";
    }

    // Save branch
    @PostMapping("/save")
    public String saveBranch(@ModelAttribute("branch") Branch branch) {
        branchService.saveBranch(branch);
        return "redirect:/branches/list";
    }

    // List all branches
    @GetMapping("/list")
    public String listBranches(Model model) {
        List<Branch> branches = branchService.getAllBranches();
        model.addAttribute("branches", branches);
        return "branchList";
    }

    // Edit branch
    @GetMapping("/edit/{id}")
    public String editBranch(@PathVariable("id") Long id, Model model) {
        Branch branch = branchService.getBranchById(id);
        model.addAttribute("branch", branch);
        return "branchForm";
    }

    // Delete branch
    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable("id") Long id) {
        branchService.deleteBranch(id);
        return "redirect:/branches/list";
    }
}
