package com.cgi.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * 
 * @author Pushpanjali
 *This the bootstrap class for spring boot application.
 */
@SpringBootApplication
@ComponentScan
public class StudentDetailsRestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentDetailsRestfulWebServicesApplication.class, args);
	}
	
}
