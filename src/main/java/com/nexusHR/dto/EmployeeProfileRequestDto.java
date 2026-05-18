package com.nexusHR.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeProfileRequestDto {

	@NotBlank(message="pan number is required")
	@Pattern(
			regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$",
			message="Invalid pan format(e.g - ABCDE1234A)"
			)
	private String panNumber;
	
	@NotBlank(message = "SSN is required")
	@Pattern(
	    regexp = "^\\d{3}-\\d{2}-\\d{4}$", 
	    message = "SSN must be in the format XXX-XX-XXXX"
	)
	private String ssn;
	
	
	@NotBlank(message="Address is required")
	@Size(min=5,max=200 , message="address must be between 5 to 200 characters")
	private String address;
	
	@NotBlank(message = "Emergency Contact is required")
	@Pattern(
	    regexp = "^[6-9]\\d{9}$", 
	    message = "Emergency contact must be a valid 10-digit mobile number"
	)
	private String emergencyContact;
	
	@NotBlank(message = "Aadhar number is required")
	@Pattern(
	    regexp = "^[2-9]{1}[0-9]{11}$", 
	    message = "Aadhar number must be 12 digits and cannot start with 0 or 1"
	)
	private String adharNumber;
	
	private Long employeeId;
}
