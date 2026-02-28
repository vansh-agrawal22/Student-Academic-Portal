package com.example.LoginProject.controller;

import com.example.LoginProject.entity.Student;
import com.example.LoginProject.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/me")
    public Student getmydetails(){
        return studentService.getLoggedInStudent();
    }
}
