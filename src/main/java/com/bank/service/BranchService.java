package com.bank.service;

import com.bank.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BranchService {
    Branch create(Branch branch);
    Branch update(Long id, Branch branch);
    void deleteById(Long id);

    Optional<Branch> findById(Long id);
    Page<Branch> page(Pageable pageable);

    boolean codeExists(String branchCode);
}
