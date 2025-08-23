// SAVE: banking-project/src/main/java/com/bank/controller/RoleController.java
package com.bank.controller;

import com.bank.entity.Role;
import com.bank.service.RoleService;
import com.bank.validator.RoleValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleValidator roleValidator;

    public RoleController(RoleService roleService, RoleValidator roleValidator) {
        this.roleService = roleService;
        this.roleValidator = roleValidator;
    }

    @InitBinder("role")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(roleValidator);
    }

    @GetMapping
    public String roleForm(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("page",
                roleService.page(PageRequest.of(page, size, Sort.by("name").ascending())));
        return "roleForm";
    }

    @PostMapping
    public String create(@ModelAttribute("role") @Valid Role role,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("page", roleService.page(PageRequest.of(0, 10, Sort.by("name"))));
            return "roleForm";
        }
        roleService.create(role);
        return "redirect:/roles";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        roleService.deleteById(id);
        return "redirect:/roles";
    }
}
