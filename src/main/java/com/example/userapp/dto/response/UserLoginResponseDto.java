package com.example.userapp.dto.response;

import lombok.Data;

@Data
public class UserLoginResponseDto {
    String email;
    String token;
}
