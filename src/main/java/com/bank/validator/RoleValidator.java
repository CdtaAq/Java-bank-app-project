// SAVE: banking-project/src/main/java/com/bank/validator/RoleValidator.java
package com.bank.validator;

import com.bank.entity.Role;
import com.bank.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.util.StringUtils;

@Component
public class RoleValidator implements Validator {

    private final RoleService roleService;

    public RoleValidator(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Role.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Role r = (Role) target;

        if (!StringUtils.hasText(r.getName())) {
            errors.rejectValue("name", "role.name.required", "Role name is required");
        } else if (r.getName().length() < 2 || r.getName().length() > 32) {
            errors.rejectValue("name", "role.name.size", "Role name must be 2-32 characters");
        } else if (roleService.existsByName(r.getName())) {
            errors.rejectValue("name", "role.name.duplicate", "Role name already exists");
        }

        if (r.getDescription() != null && r.getDescription().length() > 255) {
            errors.rejectValue("description", "role.description.size", "Description max 255 chars");
        }
    }
}
