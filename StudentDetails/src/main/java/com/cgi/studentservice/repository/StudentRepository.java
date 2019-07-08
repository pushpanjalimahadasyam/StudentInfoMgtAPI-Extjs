package com.cgi.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.studentservice.entity.Student;

/**
 * 
 * @author Pushpanjali
 * This a interface which is auto implemented with necessary methods.
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
}
