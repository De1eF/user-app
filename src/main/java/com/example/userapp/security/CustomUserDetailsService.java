package com.example.userapp.security;

import com.example.userapp.model.User;
import com.example.userapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFromDb = userService.findByEmail(username);
        User user = userFromDb.orElseThrow(
                () -> new EntityNotFoundException("No user found with username: " + username));
        UserBuilder builder = org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(r -> r.getRoleName().name())
                        .toArray(String[]::new));
        return builder.build();
    }
}
