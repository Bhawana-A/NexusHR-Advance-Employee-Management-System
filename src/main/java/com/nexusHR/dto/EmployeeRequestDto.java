package com.nexusHR.dto;

import java.time.LocalDate;
import java.util.Set;

import com.nexusHR.entity.enums.Emp_Status;
import com.nexusHR.entity.enums.Gender;

import jakarta.persistence.EnumeratedValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRequestDto {

	@NotBlank(message="first name cannot be empty")
	@Size(min=2, max=30 , message="minimum size 2 and maximum 30")
	@Pattern(regexp = "^[A-Za-z]+$" , message="First name contain only letters")
	private String firstname;
	
	@NotBlank(message = "Last name cannot be empty")
	@Size(min=2, max=30 , message="minimum size 2 and maximum 30")
	@Pattern(regexp = "^[A-Za-z]+$" , message="Last name contain only letters")
	private String lastname;
	
	@NotBlank(message="Email is required")
	@Email(message="Enter valid email")
	@Pattern(
			regexp = "^[A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
			message="Enter valid email address"
			)
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min=8,message = "password must be minimum 8 characters")
	@Pattern(
			regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&*+=!]).*$",
			message="Password must contain uppercase , lowercase , number and special character"
			)
	private String password;
	
	@Positive(message="Saalry must be greater than 0")
	private double salary;
	
	@NotBlank(message="Designation not blank")
	private String designation;
	
	@NotNull(message="joining date is required")
	private LocalDate joiningDate;
 
    @PastOrPresent(message = "Exit date cannot be in future")
    private LocalDate exitDate;
 
    @NotNull(message = "Department id is required")
    private Long departmentId;
    
    @NotBlank(message="mobile number is required")
    private String mobile_number;
    
    @NotBlank(message="required!!")
    private String dateOfBirth;
    
    private Set<Long> projectIds;
    
    @NotNull(message="required!!")
    private Emp_Status status;
    
    private boolean active=true;
  
    @NotNull(message="required!!")
    private Gender gender;
}
