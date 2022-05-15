package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UsersService {
    public User getUserName(String name);

    public List<User> getAllUsers();

    public User getUserById(long id);

    public void saveNewUser(User user, Set<Role> roles);

    public void updateUser(User userUpdate, Set<Role> roles);

    public void deleteUserById(long id);
}
