// SAVE: banking-project/src/main/java/com/bank/controller/UserController.java
package com.bank.controller;

import com.bank.entity.Role;
import com.bank.entity.User;
import com.bank.service.RoleService;
import com.bank.service.UserService;
import com.bank.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    public UserController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    private void loadLists(Model model, int page, int size) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("page",
            userService.page(PageRequest.of(page, size, Sort.by("username").ascending())));
    }

    @GetMapping
    public String userForm(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           Model model) {
        model.addAttribute("user", new User());
        loadLists(model, page, size);
        return "userForm";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            loadLists(model, 0, 10);
            return "userForm";
        }
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {
        User existing = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
        // do not send existing password to UI
        existing.setPassword("");
        existing.setConfirmPassword("");
        // ensure roles is modifiable set for JSP binding safety
        existing.setRoles(new LinkedHashSet<>(existing.getRoles()));
        model.addAttribute("user", existing);
        loadLists(model, page, size);
        return "userForm";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("user") @Valid User user,
                         BindingResult result,
                         Model model) {
        user.setId(id); // ensure validator knows it's an update
        if (result.hasErrors()) {
            loadLists(model, 0, 10);
            return "userForm";
        }
        userService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
