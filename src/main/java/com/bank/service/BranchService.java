package com.bank.service;

import com.bank.model.Branch;
import java.util.List;

public interface BranchService {
    Branch saveBranch(Branch branch);
    List<Branch> getAllBranches();
    Branch getBranchById(Long id);
    void deleteBranch(Long id);
}
