package com.example.LoginProject.repository;

import com.example.LoginProject.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findByUsername(String username);
}