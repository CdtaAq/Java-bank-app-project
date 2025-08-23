package com.example.banking.controller;

import com.example.banking.entity.Branch;
import com.example.banking.repository.BranchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branches")
public class BranchController {

    private final BranchRepository branchRepository;

    public BranchController(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @GetMapping
    public String listBranches(Model model) {
        List<Branch> branches = branchRepository.findAll();
        model.addAttribute("branches", branches);
        return "branches";
    }

    @PostMapping
    public String saveBranch(@ModelAttribute Branch branch) {
        branchRepository.save(branch);
        return "redirect:/branches";
    }
}
