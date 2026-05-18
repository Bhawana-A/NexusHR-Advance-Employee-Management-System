package com.nexusHR.service.impl;

import com.nexusHR.dto.EmployeeRequestDto;
import com.nexusHR.dto.EmployeeResponseDto;
import com.nexusHR.entity.Department;
import com.nexusHR.entity.Employee;
import com.nexusHR.entity.Project;
import com.nexusHR.entity.enums.Emp_Status;
import com.nexusHR.exception.ResourceNotFoundException;
import com.nexusHR.mapper.EmployeeMapper;
import com.nexusHR.repo.DepartmentRepo;
import com.nexusHR.repo.EmployeeRepo;
import com.nexusHR.repo.ProjectRepo;
import com.nexusHR.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
    private final ProjectRepo projectRepo;
    private final EmployeeMapper employeeMapper;

    // ================= CREATE EMPLOYEE =================
    @Override
    @Transactional // Transaction safe banane ke liye
    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {
        Employee employee = employeeMapper.toEntity(dto);
        employee.setId(null);
        
        // Department Check
        Department department = departmentRepo.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));
        employee.setDepartment(department);
        
        // STATUS Default set karna professional practice hai
        employee.setStatus(Emp_Status.ACTIVE); 
        employee.setActive(true);

        // --- SAHI TARIKA (Project Mapping) ---
        // Yahan null aur empty dono check ho rahe hain
        if (dto.getProjectIds() != null && !dto.getProjectIds().isEmpty()) {
            Set<Project> projects = dto.getProjectIds().stream()
                    .map(id -> projectRepo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id)))
                    .collect(Collectors.toSet());
            employee.setProjects(projects);
        }

        Employee saved = employeeRepo.save(employee);
        return employeeMapper.toResponseDto(saved);
    }

    // ================= GET BY ID =================
    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return employeeMapper.toResponseDto(employee);
    }

    // ================= GET ALL (LIST) =================
    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepo.findAll().stream()
                .map(employeeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ================= PAGINATION =================
    @Override
    public Page<EmployeeResponseDto> getAllEmployees(Pageable pageable) {
        return employeeRepo.findAll(pageable).map(employeeMapper::toResponseDto);
    }

 // ================= UPDATE EMPLOYEE (Corrected) =================
    @Override
    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {
        // 1. Fetch current managed entity
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        // 2. Map only basic fields (skip relationships and ID)
        employeeMapper.updateEntityFromDto(dto, employee);
        
        // 3. Handle Department (Explicitly)
        // Sirf tab update karein jab ID different ho
        if (employee.getDepartment() == null || !employee.getDepartment().getId().equals(dto.getDepartmentId())) {
            Department department = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));
            employee.setDepartment(department);
        }
        
        // 4. Handle Projects (Clear and re-add)
        if (dto.getProjectIds() != null && !dto.getProjectIds().isEmpty()) {
            Set<Project> updatedProjects = dto.getProjectIds().stream()
                    .map(pid -> projectRepo.findById(pid)
                            .orElseThrow(() -> new ResourceNotFoundException("Project not found ID: " + pid)))
                    .collect(Collectors.toSet());
            employee.setProjects(updatedProjects);
        } else {
            employee.getProjects().clear();
        }

        // 5. Save and Return
        Employee updated = employeeRepo.save(employee);
        return employeeMapper.toResponseDto(updated);
    }
    // ================= DELETE (SOFT DELETE) =================
    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        
        employee.setActive(false);
        employee.setExitDate(LocalDate.now());
        employee.setStatus(Emp_Status.RESIGNED);
        
        employeeRepo.save(employee);
    }

    // ================= TRANSFER =================
    @Override
    @Transactional
    public EmployeeResponseDto transferEmployee(Long employeeId, Long newDepartmentId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        
        Department department = departmentRepo.findById(newDepartmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        
        employee.setDepartment(department);
        return employeeMapper.toResponseDto(employeeRepo.save(employee));
    }

    // ================= PROMOTE =================
    @Override
    @Transactional
    public EmployeeResponseDto promoteEmployee(Long employeeId, String designation, double salary) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        
        employee.setDesignation(designation);
        employee.setSalary(salary);
        return employeeMapper.toResponseDto(employeeRepo.save(employee));
    }
}