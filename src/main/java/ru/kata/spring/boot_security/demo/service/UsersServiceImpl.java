package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.UsersDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersDAO usersDAO;


    public UsersServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UsersDAO usersDAO) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersDAO = usersDAO;
    }

    @Override
    public User getUserName(String name) {
        return usersDAO.getUserName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return usersDAO.getAllUsers();
    }


    @Override
    public User getUserById(long id) {
        return usersDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void saveNewUser(User user, Set<Role> roles) {
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersDAO.saveNewUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User userUpdate, Set<Role> roles) {
        String password = getUserById(userUpdate.getId()).getPassword();
        if (userUpdate.getPassword().equals(password)) {
            userUpdate.setPassword(password);
        } else {
            userUpdate.setPassword(bCryptPasswordEncoder.encode(userUpdate.getPassword()));
        }
        userUpdate.setRoles(roles);
        usersDAO.updateUser(userUpdate);
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        usersDAO.deleteUserById(id);
    }

}
