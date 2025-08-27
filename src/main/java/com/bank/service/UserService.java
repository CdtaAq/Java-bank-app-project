package com.bank.service;

import com.bank.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);                   
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    void deleteById(Long id);
}
