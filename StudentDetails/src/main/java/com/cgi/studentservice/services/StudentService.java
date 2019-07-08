package com.cgi.studentservice.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cgi.studentservice.dto.StudentDto;

/**
 * 
 * @author Pushpanjali
 * 
 * This Student service interface provides services for Student API.
 *
 */
public interface StudentService {
	/**
	 * This method is used to save student details.
	 * @param studentDto
	 * @return
	 */
    ResponseEntity<Object> saveStudent(StudentDto studentDto);
    /**
     * This method is used to get all the student information from DB.
     * @return
     */
    List<StudentDto> getAllStudents();
    /**
     * This method is used to update student details in DB.
     * @param studentDto
     * @return
     */
    ResponseEntity<Object> updateStudent(StudentDto studentDto);
    /**
     * This method is used to get page wise student information from DB.
     * @param startIndex
     * @param maxlimit
     * @return
     */
    Map<String, Object> getAllStudentsPagination(Integer startIndex, Integer maxlimit);
    
}
