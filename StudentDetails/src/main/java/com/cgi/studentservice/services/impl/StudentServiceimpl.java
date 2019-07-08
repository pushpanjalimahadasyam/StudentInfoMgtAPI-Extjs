package com.cgi.studentservice.services.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.RollbackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cgi.studentservice.converter.StudentConverter;
import com.cgi.studentservice.dto.StudentDto;
import com.cgi.studentservice.entity.Student;
import com.cgi.studentservice.repository.CustomStudentRepository;
import com.cgi.studentservice.repository.StudentRepository;
import com.cgi.studentservice.services.StudentService;
/**
 * 
 * @author Pushpanjali
 * 
 * This is the implementation of StudentService for Student API
 *
 */
@Service
public class StudentServiceimpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CustomStudentRepository customStudentRepository;

	
	/**
	 * This the implementation method, 
	 * which provides service to persist the student details to Student database.
	 */
	@Override
	public ResponseEntity<Object> saveStudent(StudentDto studentDto) {
		
		Student student = null;
		try {
			student = studentRepository.save(StudentConverter.dtoToEntity(studentDto));
		}catch(RollbackException e) {
			return ResponseEntity.badRequest().body(e);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	/**
	 * Gets all the student information
	 */
    @Override
	public List<StudentDto> getAllStudents() {
		List<StudentDto> studentDtoList = new ArrayList<StudentDto>();
		List<Student> studentList = studentRepository.findAll();
		for (Student student : studentList) {
			StudentDto studentDto = StudentConverter.entityToDto(student);
			studentDtoList.add(studentDto);
		}
		return studentDtoList;
	}

    /**
     * updates student information.
     */
	@Override
	public ResponseEntity<Object> updateStudent(StudentDto studentDto) {
		/*Optional<Student> studentOptional = studentRepository.findById(studentDto.getId());
		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();*/
		Student student = null;
		try {
			student = studentRepository.getOne(studentDto.getId());
		}catch(RollbackException e) {
			return ResponseEntity.badRequest().body(e);
		}
		if(student == null) {
			return ResponseEntity.notFound().build();
		}
		
		studentRepository.save(StudentConverter.dtoToEntity(studentDto));
		return ResponseEntity.noContent().build();		
		
	}
	/**
	 * Gets the student details for the requested page, and does the row mapping to DTO object.
	 */
	@Override
	public Map<String, Object> getAllStudentsPagination(Integer startIndex, Integer maxlimit) {
		List<StudentDto> studentDtoList = new ArrayList<StudentDto>();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Object[]> studentList = customStudentRepository.findPagedResultForStudents(startIndex, maxlimit);
		for (Object[] studentRow : studentList) {
			StudentDto studentDto = new StudentDto();
					studentDto.setId((Long)studentRow[0]);
					studentDto.setName((String)studentRow[1]);
					studentDto.setBirthDate(studentRow[2].toString());
					studentDto.setAddress((String)studentRow[3]);
					studentDto.setRegNo((Long)studentRow[4]);
			studentDtoList.add(studentDto);
		}
		modelMap.put("total", studentRepository.count());
		modelMap.put("students", studentDtoList);
		return modelMap;
	}
}
