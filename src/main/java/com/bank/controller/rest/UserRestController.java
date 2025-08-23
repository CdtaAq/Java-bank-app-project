// SAVE: banking-project/src/main/java/com/bank/controller/rest/UserRestController.java
package com.bank.controller.rest;

import com.bank.entity.User;
import com.bank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Page<User> list(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {
        return service.page(PageRequest.of(page, size, Sort.by("username").ascending()));
    }

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public User create(@RequestBody @Valid User user) {
        return service.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody @Valid User user) {
        user.setId(id);
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
