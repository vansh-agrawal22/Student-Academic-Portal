package com.example.LoginProject.Admin;
import com.example.LoginProject.entity.UserLoginCredential;
import com.example.LoginProject.repository.loginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.util.Optional;

@Component
public class AdminInitializer {

    @Autowired
    private loginRepo loginRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminIfNotExists() {

        Optional<UserLoginCredential> existingAdmin =
                loginRepo.findByUsername("vansh");

        if (existingAdmin.isEmpty()) {

            UserLoginCredential admin = new UserLoginCredential();
            admin.setUsername("vansh");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");

            loginRepo.save(admin);

            System.out.println("Default Admin Created: vansh / admin123");
        }
    }
}