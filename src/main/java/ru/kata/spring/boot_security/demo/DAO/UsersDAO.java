package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UsersDAO {
    public User getUserName(String name);

    public List<User> getAllUsers();

    public User getUserById(long id);

    public void saveNewUser(User user);

    public void updateUser(User userUpdate);

    public void deleteUserById(long id);
}
