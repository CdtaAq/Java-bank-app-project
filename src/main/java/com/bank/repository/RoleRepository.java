// SAVE: banking-project/src/main/java/com/bank/repository/RoleRepository.java
package com.bank.repository;

import com.bank.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByNameIgnoreCase(String name);
    Optional<Role> findByNameIgnoreCase(String name);
}
