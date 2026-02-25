package com.example.LoginProject.repository;

import com.example.LoginProject.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

    Student findByUsername(String username);
}