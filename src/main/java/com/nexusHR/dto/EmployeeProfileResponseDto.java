package com.nexusHR.dto;

import lombok.Data;

@Data
public class EmployeeProfileResponseDto {

	private Long id;
	private String address;
	private String emergencyContact;
	
	private Long employeeId;
	
}
