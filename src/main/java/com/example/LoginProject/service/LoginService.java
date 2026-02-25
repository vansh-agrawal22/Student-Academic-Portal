package com.example.LoginProject.service;

import com.example.LoginProject.config.JwtAuthentication;
import com.example.LoginProject.entity.UserLoginCredential;
import com.example.LoginProject.repository.loginRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // these all are beans also @component are the parent genric these all are its types
public class LoginService {

    private final loginRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthentication jwtAuthentication;

    public LoginService(loginRepo repository, PasswordEncoder passwordEncoder, JwtAuthentication jwtAuthentication) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthentication = jwtAuthentication;
    }

    public String login(String username, String password) {

        Optional<UserLoginCredential> userOpt = repository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return "User not exist";
        }

        UserLoginCredential user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid password";
        }

        String token = jwtAuthentication.generateToken(user.getUsername(), user.getRole());
        return token;

    }
}
