package com.jrp.sma;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class StudentManagementApplication{ 
	
	
    
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args); 
		

		
	}

}
