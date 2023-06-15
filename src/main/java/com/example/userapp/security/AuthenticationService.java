package com.example.userapp.security;

import com.example.userapp.exception.AuthenticationException;
import com.example.userapp.model.User;

public interface AuthenticationService {

    User login(String email, String password) throws AuthenticationException;
}
