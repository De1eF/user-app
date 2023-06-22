package com.example.userapp.security;

import com.example.userapp.exception.AuthenticationException;
import com.example.userapp.model.User;
import com.example.userapp.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String email, String password) {
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isEmpty()
                || !passwordEncoder.matches(password, userFromDb.get().getPassword())) {
            throw new AuthenticationException("Incorrect username or password!");
        }
        if (userFromDb.get().getStatus().equals(User.Status.INACTIVE)) {
            throw new AuthenticationException("User not found");
        }
        return userFromDb.get();
    }
}
