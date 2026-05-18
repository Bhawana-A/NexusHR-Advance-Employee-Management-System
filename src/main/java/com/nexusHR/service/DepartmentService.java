package com.nexusHR.service;

import com.nexusHR.dto.DepartmentRequestDto;
import com.nexusHR.dto.DepartmentResponseDto;
import com.nexusHR.entity.enums.DepartmentName;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    
	DepartmentResponseDto createDepartment(DepartmentRequestDto dto);
	 
    Page<DepartmentResponseDto> getAllDepartments(Pageable pageable);
    
    Page<DepartmentResponseDto> searchDepartments(DepartmentName name, String location, Pageable pageable);
 
    DepartmentResponseDto getDepartmentById(Long id);
 
    DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto);
 
    void deleteDepartment(Long id);
 
    void raiseSalaryForDepartment(Long departmentId, double percentage);
 
    DepartmentResponseDto getDepartmentStats(Long id);
}