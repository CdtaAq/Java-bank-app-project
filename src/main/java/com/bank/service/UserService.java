// SAVE: banking-project/src/main/java/com/bank/service/UserService.java
package com.bank.service;

import com.bank.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User create(User user);
    User update(Long id, User updated);
    void deleteById(Long id);

    Optional<User> findById(Long id);
    Page<User> page(Pageable pageable);

    boolean usernameExists(String username);
    boolean emailExists(String email);
}
