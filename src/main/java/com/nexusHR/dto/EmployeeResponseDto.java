package com.nexusHR.dto;

import java.time.LocalDate;
import java.util.Set;

import com.nexusHR.entity.enums.Emp_Status;
import com.nexusHR.entity.enums.Gender;

import lombok.Data;

@Data
public class EmployeeResponseDto {

	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private double salary;
	private String designation;
	private LocalDate joiningDate;
	private Gender gender;
	private Emp_Status status;
    private LocalDate exitDate;
    private boolean active=true;
	private String departmentName;
	private Long departmentId;
	private String mobile_number;
	private String dateOfBirth;
	private Set<String> project;
}
