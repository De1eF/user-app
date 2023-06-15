package com.example.userapp.service.impl;

import com.example.userapp.model.User;
import com.example.userapp.repository.UserRepository;
import com.example.userapp.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByUsername(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void update(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).toList();
    }
}
