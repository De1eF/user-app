package com.example.userapp.service;

import com.example.userapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;

public interface UserService {
    User add(User user);

    void update(Long id, User user);

    Optional<User> findByEmail(String email);

    User findById(Long id);

    List<User> findAll(PageRequest pageRequest);
}
