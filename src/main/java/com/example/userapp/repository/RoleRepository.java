package com.example.userapp.repository;

import com.example.userapp.model.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRoleName(UserRole.RoleName roleName);
}
