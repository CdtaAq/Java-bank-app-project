package com.bank.service.impl;

import com.bank.model.Branch;
import com.bank.repository.BranchRepository;
import com.bank.service.BranchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repo;

    public BranchServiceImpl(BranchRepository repo) {
        this.repo = repo;
    }

    @Override
    public Branch create(Branch branch) {
        branch.setBranchId(null);
        return repo.save(branch);
    }

    @Override
    public Branch update(Long id, Branch b) {
        return repo.findById(id).map(existing -> {
            existing.setBranchCode(b.getBranchCode());
            existing.setBranchName(b.getBranchName());
            existing.setBranchAddress(b.getBranchAddress());
            return repo.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Branch not found: " + id));
    }

    @Override
    public void deleteById(Long id) { repo.deleteById(id); }

    @Override
    @Transactional(readOnly = true)
    public Optional<Branch> findById(Long id) { return repo.findById(id); }

    @Override
    @Transactional(readOnly = true)
    public Page<Branch> page(Pageable pageable) { return repo.findAll(pageable); }

    @Override
    public boolean codeExists(String branchCode) {
        return branchCode != null && repo.existsByBranchCodeIgnoreCase(branchCode.trim());
    }
}
