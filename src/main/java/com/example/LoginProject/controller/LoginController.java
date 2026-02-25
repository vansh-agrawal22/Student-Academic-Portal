package com.example.LoginProject.controller;

import com.example.LoginProject.config.JwtAuthentication;
import com.example.LoginProject.entity.UserLoginCredential;
import com.example.LoginProject.service.LoginService;
import com.example.LoginProject.service.RegisterService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController //these are api and return response in jason format not in html
@RequestMapping("/api")
public class LoginController {

    private final RegisterService registerService;
    private final LoginService loginService;

    public LoginController(RegisterService registerService, LoginService loginService ) {
        this.registerService = registerService;
        this.loginService = loginService;
    }
    // Register API
    @PostMapping("/register")
    public String register(@RequestBody UserLoginCredential user) {
        registerService.register(user);
        return "User Registered Successfully!";
    }

    // Login API
    @PostMapping("/login")
    public String login(@RequestBody UserLoginCredential user) {
        return loginService.login(user.getUsername(), user.getPassword());
    }

    //to test a protected api
    @GetMapping("/profile")
    public String profile() {
        return "This is protected profile API";
    }

    @GetMapping("/Yourprofile")
    public String getProfile(Authentication authentication) {
        return "Welcome " + authentication.getName();
    }

    @GetMapping("/admin")
    public String adminOnly() {
        return "Admin access granted";
    }


}
