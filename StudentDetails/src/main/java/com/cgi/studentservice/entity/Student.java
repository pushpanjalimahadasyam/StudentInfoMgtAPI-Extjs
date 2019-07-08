package com.cgi.studentservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
/**
 * 
 * @author Pushpanjali
 * This is the entity class that maps to StudentDetails table 
 * in database with schema student
 */
@Entity
@Table(name="STUDENTDETAILS",schema="STUDENT")
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Size(min=2, message="Name should have atleast 5 characters, and max of 130 characters.")
	@Column(name="STU_NAME",nullable=false,length=130)
	private String name;

	@Past(message="Birth Date should be greater than todays date.")
	@Temporal(TemporalType.DATE)
	@Column(name = "STU_DOB",nullable=false)
	private Date birthDate;
	
	@Size(min=5, message="Address should have atleast 5 characters, and max of 255 characters.")
	@Column(name="STU_ADDRESS",nullable=false,length=255)
	private String address;
	
	//@Length(min=1, message="Registration number should have atleast 1 and max of 10 integers")
	@Column(name="STU_REGNO",nullable=false,length=10,unique=true)
	private Long regNo;

	public Student() {

	}
	public Student(Long id, String name, Date birthDate,String address,long regNo) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.address= address;
		this.regNo=regNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getRegNo() {
		return regNo;
	}

	public void setRegNo(long regNo) {
		this.regNo = regNo;
	}

	@Override
	public String toString() {
		return String.format("Student [id=%s, name=%s, birthDate=%s, address=%s,regNo=%s]", id, name, birthDate, address,regNo);
	}


}
