package com.nexusHR.dto;

import com.nexusHR.entity.enums.DepartmentName;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentRequestDto {

	@NotNull(message = "Department name is required") // Enum ke liye @NotNull use karein
	private DepartmentName name;
	
	@NotBlank(message = "Location is required")
	@Size(min=2, max=100 , message="Location must be valid")
	private String location;
	
	@Min(value=0 , message="budget cannot be nagative")
	private double budget;
	
	@NotBlank(message="Deapartment head is required")
	@Pattern(regexp = "^[A-Za-z .]+$" , message="Only letters allowed in department head name")
//	@JsonProperty("deptHead") // Ye annotation key ko map kar degi
	private String departmentHead;
	
	
}
