// SAVE: banking-project/src/main/java/com/bank/service/impl/UserServiceImpl.java
package com.bank.service.impl;

import com.bank.entity.User;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        // always encode incoming password
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User update(Long id, User updated) {
        return repo.findById(id).map(existing -> {
            existing.setUsername(updated.getUsername());
            existing.setEmail(updated.getEmail());
            existing.setEnabled(updated.isEnabled());
            existing.setRoles(updated.getRoles());
            if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
                existing.setPassword(passwordEncoder.encode(updated.getPassword()));
            }
            return repo.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> page(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public boolean usernameExists(String username) {
        return username != null && repo.existsByUsernameIgnoreCase(username.trim());
    }

    @Override
    public boolean emailExists(String email) {
        return email != null && repo.existsByEmailIgnoreCase(email.trim());
    }
}
