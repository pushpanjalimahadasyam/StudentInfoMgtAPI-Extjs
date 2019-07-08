package com.cgi.studentservice.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.studentservice.dto.StudentDto;
import com.cgi.studentservice.services.StudentService;
/**
 * 
 * @author Pushpanjali.
 * 
 * This the rest controller class which provides
 *  the rest api's for student that can be consumed by clients.
 *
 */
@RestController
public class StudentResource {
	@Autowired
	StudentService studentService;
	
	/**
	 * This method works on GET request mapping, 
	 * to fetch the student details.
	 * @return
	 */
	@RequestMapping(value="/students", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StudentDto> getAllStudents() {
		return studentService.getAllStudents();
	}
	/**
	 *This method works on GET request mapping, 
	 * to fetch the student details, for the requested page.
	 * @param startIndex
	 * @param maxlimit
	 * @return
	 */
	@RequestMapping(value="/students/paging", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAllStudentsPagination(@RequestParam("start") Integer startIndex,
			@RequestParam("limit") Integer maxlimit) {
		return studentService.getAllStudentsPagination(startIndex,maxlimit);		
	}
	/**
	 * This method works on POST request mapping.
	 * This method provides us a service to save student details.
	 * @param studentDto
	 * @return
	 */
	@RequestMapping(value="/students", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveStudent(@Valid @RequestBody StudentDto studentDto) {
		return studentService.saveStudent(studentDto);
	}
	
	/**
	 * This method works on PUT request mapping.
	 * This method provides us a service to update student details.
	 * @param studentDto
	 * @return
	 */
	@RequestMapping(value="/students", method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateStudent(@Valid @RequestBody StudentDto studentDto) {
		return studentService.updateStudent(studentDto);
	}
	
}
