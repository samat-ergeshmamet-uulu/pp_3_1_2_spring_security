package com.security.services;

import com.security.entities.Role;

import java.util.List;

public interface RoleService {

    Role findByName(String roleName);

    List<Role> findAll();
}
