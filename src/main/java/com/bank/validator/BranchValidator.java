package com.bank.validator;

import com.bank.model.Branch;
import com.bank.service.BranchService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BranchValidator implements Validator {

    private final BranchService branchService;

    public BranchValidator(BranchService branchService) {
        this.branchService = branchService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Branch.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Branch b = (Branch) target;

        if (!StringUtils.hasText(b.getBranchCode())) {
            errors.rejectValue("branchCode", "branch.code.required", "Branch code is required");
        } else if (b.getBranchCode().length() < 3 || b.getBranchCode().length() > 16) {
            errors.rejectValue("branchCode", "branch.code.size", "Code must be 3–16 chars");
        } else if (b.getBranchId() == null && branchService.codeExists(b.getBranchCode())) {
            errors.rejectValue("branchCode", "branch.code.duplicate", "Branch code already exists");
        }

        if (!StringUtils.hasText(b.getBranchName())) {
            errors.rejectValue("branchName", "branch.name.required", "Branch name is required");
        } else if (b.getBranchName().length() < 3 || b.getBranchName().length() > 64) {
            errors.rejectValue("branchName", "branch.name.size", "Name must be 3–64 chars");
        }

        if (b.getBranchAddress() != null && b.getBranchAddress().length() > 255) {
            errors.rejectValue("branchAddress", "branch.addr.size", "Address max 255 chars");
        }
    }
}
