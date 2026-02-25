package com.example.LoginProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class StudentEntry {

        private String username;
        private String fullName;
        private String rollNumber;
        private String department;
        private int semester;
        private double cgpa;
        private double attendance;
        private String tempPassword;   // 👈 important

    }

