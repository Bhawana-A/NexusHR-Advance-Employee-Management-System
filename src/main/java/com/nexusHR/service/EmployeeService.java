package com.nexusHR.service;

import com.nexusHR.dto.EmployeeRequestDto;
import com.nexusHR.dto.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeService {
	
    EmployeeResponseDto createEmployee(EmployeeRequestDto dto);
    EmployeeResponseDto getEmployeeById(Long id);
    List<EmployeeResponseDto> getAllEmployees();
    Page<EmployeeResponseDto> getAllEmployees(Pageable pageable);
    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto);
    void deleteEmployee(Long id);
    EmployeeResponseDto transferEmployee(Long employeeId, Long newDepartmentId);
    EmployeeResponseDto promoteEmployee(Long employeeId, String designation, double salary);
    
}