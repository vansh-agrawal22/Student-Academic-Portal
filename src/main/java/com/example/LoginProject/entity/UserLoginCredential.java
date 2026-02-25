package com.example.LoginProject.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usersLogin")// this @document means it is a table in DB with name userLogin
@Data // lomboc annotation to not write cpnstructor and getter and setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginCredential {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String role;

}
