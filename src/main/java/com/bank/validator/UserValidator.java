// SAVE: banking-project/src/main/java/com/bank/validator/UserValidator.java
package com.bank.validator;

import com.bank.entity.User;
import com.bank.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User u = (User) target;

        // username
        if (!StringUtils.hasText(u.getUsername())) {
            errors.rejectValue("username", "user.username.required", "Username is required");
        } else if (u.getUsername().length() < 3 || u.getUsername().length() > 32) {
            errors.rejectValue("username", "user.username.size", "Username must be 3-32 characters");
        } else {
            userRepository.findByUsernameIgnoreCase(u.getUsername()).ifPresent(existing -> {
                if (u.getId() == null || !existing.getId().equals(u.getId())) {
                    errors.rejectValue("username", "user.username.duplicate", "Username already exists");
                }
            });
        }

        // email
        if (!StringUtils.hasText(u.getEmail())) {
            errors.rejectValue("email", "user.email.required", "Email is required");
        } else {
            userRepository.findByEmailIgnoreCase(u.getEmail()).ifPresent(existing -> {
                if (u.getId() == null || !existing.getId().equals(u.getId())) {
                    errors.rejectValue("email", "user.email.duplicate", "Email already exists");
                }
            });
        }

        // password rules:
        if (u.getId() == null) {
            // create: password required
            if (!StringUtils.hasText(u.getPassword())) {
                errors.rejectValue("password", "user.password.required", "Password is required");
            } else if (u.getPassword().length() < 6) {
                errors.rejectValue("password", "user.password.size", "Password must be at least 6 characters");
            }
            if (!StringUtils.hasText(u.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "user.confirm.required", "Confirm password is required");
            } else if (StringUtils.hasText(u.getPassword()) && !u.getPassword().equals(u.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "user.confirm.match", "Passwords do not match");
            }
        } else {
            // update: password optional; if provided must meet rules & match
            if (StringUtils.hasText(u.getPassword())) {
                if (u.getPassword().length() < 6) {
                    errors.rejectValue("password", "user.password.size", "Password must be at least 6 characters");
                }
                if (!u.getPassword().equals(u.getConfirmPassword())) {
                    errors.rejectValue("confirmPassword", "user.confirm.match", "Passwords do not match");
                }
            }
        }

        // at least one role (optional requirement—enable if you want to force a role)
        if (u.getRoles() == null || u.getRoles().isEmpty()) {
            errors.rejectValue("roles", "user.roles.required", "Select at least one role");
        }
    }
}
