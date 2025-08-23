// SAVE: banking-project/src/main/java/com/bank/service/RoleService.java
package com.bank.service;

import com.bank.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role create(Role role);
    void deleteById(Long id);
    Optional<Role> findById(Long id);
    List<Role> findAll();
    Page<Role> page(Pageable pageable);
    boolean existsByName(String name);
}
