package com.example.LoginProject.service;

import com.example.LoginProject.DTO.StudentEntry;
import com.example.LoginProject.entity.Student;
import com.example.LoginProject.entity.UserLoginCredential;
import com.example.LoginProject.repository.StudentRepository;
import com.example.LoginProject.repository.loginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private loginRepo loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createStudent(StudentEntry request) {

        if (request.getTempPassword() == null || request.getTempPassword().isBlank()) {
            throw new RuntimeException("Temporary password is required.");
        }

        // 1. Save student details
        Student student = new Student();
        student.setUsername(request.getUsername());
        student.setFullName(request.getFullName());
        student.setRollNumber(request.getRollNumber());
        student.setDepartment(request.getDepartment());
        student.setSemester(request.getSemester());
        student.setCgpa(request.getCgpa());
        student.setAttendance(request.getAttendance());
        studentRepository.save(student);

        // 2. Create login credentials so student can login
        UserLoginCredential credential = new UserLoginCredential();
        credential.setUsername(request.getUsername());
        credential.setPassword(passwordEncoder.encode(request.getTempPassword()));
        credential.setRole("STUDENT");
        loginRepository.save(credential);
    }

    public Student getLoggedInStudent() {
        // Get username from JWT (set by JwtValidation filter)
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return studentRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found: " + username));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}