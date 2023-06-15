package com.example.userapp.service.impl;

import com.example.userapp.model.UserRole;
import com.example.userapp.repository.RoleRepository;
import com.example.userapp.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public UserRole add(UserRole role) {
        return roleRepository.save(role);
    }

    @Override
    public UserRole findByRoleName(String roleName) {
        return roleRepository.findByRoleName(UserRole.RoleName.valueOf(roleName.toUpperCase())).orElseThrow(
                () -> new EntityNotFoundException("Role not found with roleName: " + roleName));
    }
}
