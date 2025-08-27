package com.bank.config;

import com.bank.entity.Role;
import com.bank.entity.User;
import com.bank.repository.RoleRepository;
import com.bank.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seed(RoleRepository roleRepo,
                           UserRepository userRepo,
                           PasswordEncoder encoder) {
        return args -> {
            // Ensure roles exist
            Role adminRole = roleRepo.findByRoleName("ROLE_ADMIN").orElseGet(() ->
                roleRepo.save(Role.builder().roleName("ROLE_ADMIN").build())
            );
            Role userRole = roleRepo.findByRoleName("ROLE_USER").orElseGet(() ->
                roleRepo.save(Role.builder().roleName("ROLE_USER").build())
            );

            // Ensure Admin user exists
            if (!userRepo.existsByUsername("Admin")) {
                User admin = new User();
                admin.setUsername("Admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setEnabled(true);
                admin.setRoles(new HashSet<>());
                admin.getRoles().add(adminRole);
                userRepo.save(admin);
            }

            // Ensure normal User exists
            if (!userRepo.existsByUsername("User")) {
                User basic = new User();
                basic.setUsername("User");
                basic.setPassword(encoder.encode("user123"));
                basic.setEnabled(true);
                basic.setRoles(new HashSet<>());
                basic.getRoles().add(userRole);
                userRepo.save(basic);
            }
        };
    }
}
