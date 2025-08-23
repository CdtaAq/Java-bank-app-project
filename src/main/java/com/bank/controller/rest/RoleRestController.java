// SAVE: banking-project/src/main/java/com/bank/controller/rest/RoleRestController.java
package com.bank.controller.rest;

import com.bank.entity.Role;
import com.bank.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {

    private final RoleService service;

    public RoleRestController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Role> list(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {
        return service.page(PageRequest.of(page, size, Sort.by("name").ascending()));
    }

    @GetMapping("/{id}")
    public Optional<Role> get(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Role create(@RequestBody @Valid Role role) { return service.create(role); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
