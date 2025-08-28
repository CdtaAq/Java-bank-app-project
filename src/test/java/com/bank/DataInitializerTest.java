package com.bank;

import com.bank.entity.Role;
import com.bank.entity.User;
import com.bank.repository.RoleRepository;
import com.bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// spins up the context and runs DataInitializer
@SpringBootTest
public class DataInitializerTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void rolesAndDefaultUsersAreSeeded() {
        // assert roles exist
        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN").orElse(null);
        Role userRole  = roleRepository.findByRoleName("ROLE_USER").orElse(null);

        assert adminRole != null : "ROLE_ADMIN should be created";
        assert userRole  != null : "ROLE_USER should be created";

        // assert users exist
        User admin = userRepository.findByUsername("Admin").orElse(null);
        User basic = userRepository.findByUsername("User").orElse(null);

        assert admin != null : "Admin user should be created";
        assert basic != null : "User user should be created";

        // check roles assigned
        assert admin.getRoles().stream().anyMatch(r -> "ROLE_ADMIN".equals(r.getRoleName()));
        assert basic.getRoles().stream().anyMatch(r -> "ROLE_USER".equals(r.getRoleName()));
    }
}
