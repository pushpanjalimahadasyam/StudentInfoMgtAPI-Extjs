package com.cgi.studentservice.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cgi.studentservice.dto.StudentDto;
import com.cgi.studentservice.entity.Student;
/**
 * 
 * @author Pushpanjali
 * This class is a utility class to convert 
 * entity data to DTO objects and vice versa.
 *
 */
public class StudentConverter {
	/**
	 * This method converts DTO(form Data) to Entity for persistence.
	 * @param studentDto
	 * @return
	 */
	public static Student dtoToEntity(StudentDto studentDto){
		Student student = new Student();
		student.setId(studentDto.getId());
		student.setAddress(studentDto.getAddress());
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd"); 
			Date date = (Date)formatter.parse(studentDto.getBirthDate());
			student.setBirthDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		student.setName(studentDto.getName());	
		student.setRegNo(studentDto.getRegNo());
		return student;
	}

	/**
	 * This method converts Entity(DB Data) to DTO for UI layer.
	 * @param student
	 * @return
	 */
	public static StudentDto entityToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setAddress(student.getAddress());
		studentDto.setBirthDate(student.getBirthDate().toString());
		studentDto.setName(student.getName());	
		studentDto.setRegNo(student.getRegNo());
		return studentDto;
	}
}
