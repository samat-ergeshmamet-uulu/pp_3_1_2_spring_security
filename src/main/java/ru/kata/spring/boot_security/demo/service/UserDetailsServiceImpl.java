package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.UsersDAO;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersDAO usersDAO;

    public UserDetailsServiceImpl(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = usersDAO.getUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with that name");
        }
        return user;
    }
}