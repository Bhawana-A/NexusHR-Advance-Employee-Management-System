package com.nexusHR.dto;

import java.util.List;

import com.nexusHR.entity.enums.DepartmentName;

import lombok.Data;

@Data
public class DepartmentResponseDto {

	private Long id;
	private DepartmentName name;
	private double budget;
	private String departmentHead;
	private String location;
	private boolean active;
	private int totalEmployee;
	
	private List<EmployeeResponseDto> employee;
	
}
