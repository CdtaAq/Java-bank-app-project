package com.bank.controller;

import com.bank.entity.Role;
import com.bank.entity.User;
import com.bank.service.RoleService;
import com.bank.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService; this.roleService = roleService;
    }

    // Admin can see list; non-admin is blocked by SecurityConfig
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    // Shared: Admin sees full blank form; non-admin sees only self (read/edit own basic fields)
    @GetMapping("/form")
    public String form(Authentication auth, Model model) {
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            model.addAttribute("user", new User());
            model.addAttribute("allRoles", roleService.findAll());
        } else {
            User me = userService.findByUsername(auth.getName()).orElseThrow();
            model.addAttribute("user", me);
            // roles list only for display; non-admin cannot change roles
            model.addAttribute("allRoles", Collections.emptyList());
        }
        return "userForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User incoming, Authentication auth) {
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            // Non-admin can only update their own basic fields; ignore roles changes
            User me = userService.findByUsername(auth.getName()).orElseThrow();
            me.setFirstName(incoming.getFirstName());
            me.setLastName(incoming.getLastName());
            me.setEmail(incoming.getEmail());
            if (incoming.getPassword() != null && !incoming.getPassword().isBlank()) {
                me.setPassword(incoming.getPassword());
            }
            userService.save(me);
            return "redirect:/users/form";
        }

        // Admin path: can create/update any user and assign roles
        // If roles were sent as role IDs, ensure binding; otherwise default ROLE_USER enforced in service.
        userService.save(incoming);
        return "redirect:/users/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Authentication auth, Model model) {
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        User target = userService.findById(id).orElseThrow();

        if (!isAdmin && !auth.getName().equalsIgnoreCase(target.getUsername())) {
            // non-admin cannot edit others
            return "redirect:/users/form";
        }

        model.addAttribute("user", target);
        model.addAttribute("allRoles", isAdmin ? roleService.findAll() : Collections.emptyList());
        return "userForm";
    }

    // Admin only (also secured in SecurityConfig)
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "userForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users/list";
    }
}
