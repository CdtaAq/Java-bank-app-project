package com.bank.repository;

import com.bank.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    boolean existsByBranchCodeIgnoreCase(String branchCode);
}
