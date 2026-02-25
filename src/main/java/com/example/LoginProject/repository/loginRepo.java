package com.example.LoginProject.repository;

import com.example.LoginProject.entity.UserLoginCredential;// this will import the package of entity with its file
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface  loginRepo extends MongoRepository<UserLoginCredential, String> {
    Optional<UserLoginCredential> findByUsername(String username);
    long deleteByUsername(String username);
}
