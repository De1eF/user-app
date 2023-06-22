package com.example.userapp.service.impl;

import com.example.userapp.exception.NotFoundException;
import com.example.userapp.model.User;
import com.example.userapp.repository.UserRepository;
import com.example.userapp.service.RoleService;
import com.example.userapp.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleService.findByRoleName("USER"));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByUsername(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    @Override
    public void update(Long id, User user) {
        User userFromDb = findById(id);
        if (userFromDb.getStatus().equals(User.Status.INACTIVE)) {
            throw new RuntimeException("User not found");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userFromDb.getRoles());
        user.setStatus(userFromDb.getStatus());
        userFromDb.setId(id);
        userRepository.save(userFromDb);
    }

    @Override
    public List<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).toList();
    }
}
