package com.example.userapp.service;

import com.example.userapp.model.UserRole;

public interface RoleService {
    UserRole add(UserRole role);

    UserRole findByRoleName(String roleName);
}
