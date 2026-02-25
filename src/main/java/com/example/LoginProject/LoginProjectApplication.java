package com.example.LoginProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginProjectApplication.class, args);
	}

}

//@beans are use to handle the class without making obejct for each class
//@Autowired this is used to add dependency of other class to this class but in project nowadays contructor dependency is better to use
