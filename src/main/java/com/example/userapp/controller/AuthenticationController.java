package com.example.userapp.controller;

import com.example.userapp.dto.request.UserLoginRequestDto;
import com.example.userapp.dto.response.UserLoginResponseDto;
import com.example.userapp.model.User;
import com.example.userapp.security.AuthenticationService;
import com.example.userapp.security.jwt.JwtTokenProvider;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto userLoginDto) {
        User user = authenticationService.login(userLoginDto.getLogin(),
                userLoginDto.getPassword());
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.toList()));
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        userLoginResponseDto.setEmail(userLoginResponseDto.getEmail());
        userLoginResponseDto.setToken(token);
        return userLoginResponseDto;
    }
}
