package com.example.LoginProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtValidation jwtValidation;

    public SecurityConfig(JwtValidation jwtValidation) {
        this.jwtValidation = jwtValidation;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // disable CSRF for APIs
                .csrf(csrf -> csrf.disable())

                // authorization rules
                .authorizeHttpRequests(auth -> auth

                        // public HTML / CSS / JS
                        .requestMatchers(
                                "/login.html",
                                "/register.html",
                                "/dashboard.html",
                                "/style.css",
                                "/app.js",
                                "/dashboard.css",
                                "/dashboard.js",
                                "/favicon.ico"
                        ).permitAll()

                        // public APIs
                        .requestMatchers(HttpMethod.POST,
                                "/api/login",
                                "/api/register"
                        ).permitAll()

                        .requestMatchers("/api/admin").hasRole("ADMIN")
                        // everything else requires JWT
                        .anyRequest().authenticated()
                )
                // JWT filter
                .addFilterBefore(jwtValidation, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
