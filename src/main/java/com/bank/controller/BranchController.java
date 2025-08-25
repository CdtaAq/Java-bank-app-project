package com.bank.controller;

import com.bank.model.Branch;
import com.bank.service.BranchService;
import com.bank.validator.BranchValidator;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/branches")
public class BranchController {

    private final BranchService service;
    private final BranchValidator validator;

    public BranchController(BranchService service, BranchValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @InitBinder("branch")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public String form(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(defaultValue = "branchName") String sort,
                       @RequestParam(defaultValue = "asc") String dir,
                       Model model) {
        model.addAttribute("branch", new Branch());
        Sort s = dir.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        model.addAttribute("page", service.page(PageRequest.of(page, size, s)));
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        return "branchForm";
    }

    @PostMapping
    public String create(@ModelAttribute("branch") @Valid Branch branch,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("page", service.page(PageRequest.of(0, 10, Sort.by("branchName"))));
            model.addAttribute("sort", "branchName");
            model.addAttribute("dir", "asc");
            return "branchForm";
        }
        service.create(branch);
        return "redirect:/branches";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(defaultValue = "branchName") String sort,
                       @RequestParam(defaultValue = "asc") String dir,
                       Model model) {
        Branch b = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Branch not found: " + id));
        model.addAttribute("branch", b);
        Sort s = dir.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        model.addAttribute("page", service.page(PageRequest.of(page, size, s)));
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        return "branchForm";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("branch") @Valid Branch branch,
                         BindingResult result,
                         Model model) {
        branch.setBranchId(id);
        if (result.hasErrors()) {
            model.addAttribute("page", service.page(PageRequest.of(0, 10, Sort.by("branchName"))));
            model.addAttribute("sort", "branchName");
            model.addAttribute("dir", "asc");
            return "branchForm";
        }
        service.update(id, branch);
        return "redirect:/branches";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/branches";
    }
}
