package com.example.userapp.repository;

import com.example.userapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findUserByUsername(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id")
    Optional<User> findUserById(Long id);

    @Override
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.status = 'ACTIVE'")
    Page<User> findAll(Pageable pageable);
}
