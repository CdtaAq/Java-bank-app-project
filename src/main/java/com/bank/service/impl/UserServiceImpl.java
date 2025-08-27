package com.bank.service.impl;

import com.bank.entity.Role;
import com.bank.entity.User;
import com.bank.repository.RoleRepository;
import com.bank.repository.UserRepository;
import com.bank.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    @Override
    public User save(User user) {
        // encode password if raw (basic check)
        if (user.getPassword() != null && !user.getPassword().startsWith("{bcrypt}")) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        // default ROLE_USER for new users with no roles
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role userRole = roleRepo.findByRoleName("ROLE_USER")
                    .orElseGet(() -> roleRepo.save(Role.builder().roleName("ROLE_USER").build()));
            user.getRoles().add(userRole);
        }
        return userRepo.save(user);
    }

    @Override public List<User> findAll() { return userRepo.findAll(); }
    @Override public Optional<User> findById(Long id) { return userRepo.findById(id); }
    @Override public Optional<User> findByUsername(String username) { return userRepo.findByUsername(username); }
    @Override public void deleteById(Long id) { userRepo.deleteById(id); }
}
