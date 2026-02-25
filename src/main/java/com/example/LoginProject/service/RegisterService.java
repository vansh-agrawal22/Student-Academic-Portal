package com.example.LoginProject.service;

import com.example.LoginProject.config.JwtAuthentication;
import com.example.LoginProject.entity.UserLoginCredential;
import com.example.LoginProject.repository.loginRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service// we use contructor dependecny which is better to inject dependecny in sted of using autwired
@Component
public class RegisterService {

    private final loginRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthentication jwtAuthentication;

    public RegisterService(loginRepo repository, PasswordEncoder passwordEncoder, JwtAuthentication jwtAuthentication) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthentication = jwtAuthentication;
    }

    public UserLoginCredential register(UserLoginCredential user) {
        System.out.println("Saving user: " + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("Student");// default is user
        return repository.save(user);
    }

}
