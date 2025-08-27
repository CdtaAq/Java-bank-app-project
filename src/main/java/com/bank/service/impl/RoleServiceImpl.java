package com.bank.service.impl;

import com.bank.entity.Role;
import com.bank.repository.RoleRepository;
import com.bank.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override public Role save(Role role) { return roleRepo.save(role); }
    @Override public List<Role> findAll() { return roleRepo.findAll(); }
    @Override public Optional<Role> findById(Long id) { return roleRepo.findById(id); }
    @Override public Optional<Role> findByRoleName(String name) { return roleRepo.findByRoleName(name); }
    @Override public void deleteById(Long id) { roleRepo.deleteById(id); }
}
