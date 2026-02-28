package com.example.LoginProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
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

                        // public files
                        .requestMatchers(
                                "/login.html",
                                "/style.css",
                                "/login.js",
                                "/admin-dashboard.html",
                                "/admin-dashboard.css",
                                "/admin-dashboard.js",
                                "/student-dashboard.html",
                                "/student-dashboard.css",
                                "/student-dashboard.js",
                                "/*.html",
                                "/*.js",
                                "/*.css"
                        ).permitAll()

                        // login API
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()

                        // protected APIs
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/student/**").hasRole("STUDENT")

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
