package com.bank.controller.rest;

import com.bank.model.Branch;
import com.bank.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/branches")
public class BranchRestController {

    private final BranchService service;

    public BranchRestController(BranchService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Branch> list(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "branchName") String sort,
                             @RequestParam(defaultValue = "asc") String dir) {
        Sort s = dir.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        return service.page(PageRequest.of(page, size, s));
    }

    @GetMapping("/{id}")
    public Optional<Branch> get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Branch create(@RequestBody @Valid Branch branch) {
        return service.create(branch);
    }

    @PutMapping("/{id}")
    public Branch update(@PathVariable Long id, @RequestBody @Valid Branch branch) {
        branch.setBranchId(id);
        return service.update(id, branch);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
