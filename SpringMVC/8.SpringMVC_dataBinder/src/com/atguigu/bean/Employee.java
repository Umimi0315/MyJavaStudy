package com.atguigu.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

public class Employee {

	private Integer id;
	@NotEmpty
	@Length(min = 6,max = 8)
	private String lastName;

	@Email
	private String email;
	//1 male, 0 female
	private Integer gender;

	//规定提交的日期格式
	//@Past:必须是一个过去的时间
	//@Future:必须是一个未来的时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	@JsonFormat(pattern = "yyyy-HH-dd")
	private Date birth=new Date();

	@JsonIgnore
	private Department department;

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee(Integer id, String lastName, String email, Integer gender,
			Department department) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.department = department;
	}

	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email="
				+ email + ", gender=" + gender + ", department=" + department
				+ "]";
	}
	
	
}
