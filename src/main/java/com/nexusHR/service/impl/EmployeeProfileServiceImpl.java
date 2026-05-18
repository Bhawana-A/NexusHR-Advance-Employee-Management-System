package com.nexusHR.service.impl;

import org.springframework.stereotype.Service;

import com.nexusHR.dto.EmployeeProfileRequestDto;
import com.nexusHR.dto.EmployeeProfileResponseDto;
import com.nexusHR.entity.Employee;
import com.nexusHR.entity.EmployeeProfile;
import com.nexusHR.exception.ResourceNotFoundException;
import com.nexusHR.mapper.EmployeeProfileMapper;
import com.nexusHR.repo.EmployeeProfileRepo;
import com.nexusHR.repo.EmployeeRepo;
import com.nexusHR.service.EmployeeProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeProfileServiceImpl implements EmployeeProfileService{
	
	private final EmployeeProfileRepo employeeProfileRepo;
	private final EmployeeRepo employeeRepo;
    private final EmployeeProfileMapper employeeProfileMapper;

    
    @Override
	public EmployeeProfileResponseDto createEmployeeProfile(EmployeeProfileRequestDto dto) {
		Employee employee = employeeRepo.findById(dto.getEmployeeId())
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
 
		EmployeeProfile profile = employeeProfileMapper.toEntity(dto);
		profile.setId(null);
 
		profile.setEmployee(employee);
 
		EmployeeProfile saved = employeeProfileRepo.save(profile);
 
		return employeeProfileMapper.toResponseDto(saved);
	}

    @Override
   	public EmployeeProfileResponseDto getProfileByEmployeeId(Long employeeId) {
   		EmployeeProfile profile = employeeProfileRepo.findByEmployee_id(employeeId)
   				.orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    
   		return employeeProfileMapper.toResponseDto(profile);
   	}


    @Override
	public EmployeeProfileResponseDto updateProfile(Long employeeId, EmployeeProfileRequestDto dto) {
		EmployeeProfile profile = employeeProfileRepo.findByEmployee_id(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
 
		employeeProfileMapper.updateEntity(dto, profile);
 
		EmployeeProfile updated = employeeProfileRepo.save(profile);
 
		return employeeProfileMapper.toResponseDto(updated);
	}


	@Override
	public void deleteProfile(Long employeeId) {
		EmployeeProfile profile = employeeProfileRepo.findByEmployee_id(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
 
		employeeProfileRepo.delete(profile);
	}
	

	

}
