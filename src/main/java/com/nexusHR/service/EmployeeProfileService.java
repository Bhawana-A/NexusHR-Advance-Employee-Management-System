package com.nexusHR.service;

import com.nexusHR.dto.EmployeeProfileResponseDto;
import com.nexusHR.dto.EmployeeProfileRequestDto;

public interface EmployeeProfileService {

	EmployeeProfileResponseDto createEmployeeProfile(EmployeeProfileRequestDto dto);
	EmployeeProfileResponseDto getProfileByEmployeeId(Long employeeId);
	EmployeeProfileResponseDto updateProfile(Long employeeId, EmployeeProfileRequestDto dto);
	void deleteProfile(Long employeeId);
 
	
}
