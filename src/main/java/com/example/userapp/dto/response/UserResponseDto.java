package com.example.userapp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String status;
    private String createdAt;
    private List<String> roles;
}
