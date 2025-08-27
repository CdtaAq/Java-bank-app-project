package com.bank.service;

import com.bank.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role save(Role role);
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Optional<Role> findByRoleName(String name);
    void deleteById(Long id);
}
