// SAVE: banking-project/src/main/java/com/bank/service/impl/RoleServiceImpl.java
package com.bank.service.impl;

import com.bank.entity.Role;
import com.bank.repository.RoleRepository;
import com.bank.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repo;

    public RoleServiceImpl(RoleRepository repo) {
        this.repo = repo;
    }

    @Override
    public Role create(Role role) {
        // For this assignment update isn't required; create = save new
        role.setId(null);
        return repo.save(role);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<Role> page(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public boolean existsByName(String name) {
        return name != null && repo.existsByNameIgnoreCase(name.trim());
    }
}
