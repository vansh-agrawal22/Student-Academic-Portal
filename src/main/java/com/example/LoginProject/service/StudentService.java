package com.example.LoginProject.service;
import com.example.LoginProject.DTO.StudentEntry;
import com.example.LoginProject.entity.Student;
import com.example.LoginProject.entity.UserLoginCredential;
import com.example.LoginProject.repository.StudentRepository;
import com.example.LoginProject.repository.loginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private loginRepo loginRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createStudent(StudentEntry request) {

        // 1️⃣ Create login account
        UserLoginCredential user = new UserLoginCredential();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getTempPassword()));
        user.setRole("STUDENT");

        loginRepo.save(user);

        // 2️⃣ Create student academic entity
        Student student = new Student();
        student.setUsername(request.getUsername());
        student.setFullName(request.getFullName());
        student.setRollNumber(request.getRollNumber());
        student.setDepartment(request.getDepartment());
        student.setSemester(request.getSemester());
        student.setCgpa(request.getCgpa());
        student.setAttendance(request.getAttendance());

        studentRepository.save(student);
    }
}