package com.security.services;

import com.security.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void deleteUser(Long id);
    void update(Long id, User updatedUser);
    User findByUsername(String username);
}
