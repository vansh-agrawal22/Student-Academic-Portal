package com.example.LoginProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    private String id;

    private String username;   // link to login account
    private String fullName;
    private String rollNumber;
    private String department;
    private int semester;

    private double cgpa;
    private double attendance;


}