package com.example.LoginProject.controller;

import com.example.LoginProject.DTO.StudentEntry;
import com.example.LoginProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    // 👑 Admin creates student
    @PostMapping("/create-student")
    public String createStudent(@RequestBody StudentEntry request) {

        studentService.createStudent(request);

        return "Student created successfully";
    }
}