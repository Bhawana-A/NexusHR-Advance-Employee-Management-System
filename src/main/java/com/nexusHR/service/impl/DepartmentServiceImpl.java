package com.nexusHR.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nexusHR.dto.DepartmentRequestDto;
import com.nexusHR.dto.DepartmentResponseDto;
import com.nexusHR.entity.Department;
import com.nexusHR.entity.Employee;
import com.nexusHR.entity.enums.DepartmentName;
import com.nexusHR.exception.ResourceNotFoundException;
import com.nexusHR.mapper.DepartmentMapper;
import com.nexusHR.repo.DepartmentRepo;
import com.nexusHR.repo.EmployeeRepo;
import com.nexusHR.service.DepartmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentRepo departmentRepository;
	private final EmployeeRepo employeeRepository;
	private final DepartmentMapper departmentMapper;

	
	//@RequiredArgsConstructor - 
//	public DepartmentServiceImpl(DepartmentRepo departmentRepository, EmployeeRepo employeeRepository,
//			DepartmentMapper departmentMapper) {
//		this.departmentRepository = departmentRepository;
//		this.employeeRepository = employeeRepository;
//		this.departmentMapper = departmentMapper;
//	}

	// ================= CREATE =================

	@Override
	public DepartmentResponseDto createDepartment(DepartmentRequestDto dto) {

		Department department = departmentMapper.toEntity(dto);

		Department saved = departmentRepository.save(department);

		return departmentMapper.toResponseDto(saved);
	}

// ================= GET ALL WITH PAGINATION =================

	@Override
	public Page<DepartmentResponseDto> getAllDepartments(Pageable pageable) {

		return departmentRepository.findAll(pageable).map(departmentMapper::toResponseDto);
	}

// ================= GET BY ID =================

	@Override
	public DepartmentResponseDto getDepartmentById(Long id) {

		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found"));

		return departmentMapper.toResponseDto(department);
	}

	// ================= UPDATE =================

	@Override
	public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto) {

		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found"));

		departmentMapper.updateEntity(dto, department);

		Department updated = departmentRepository.save(department);

		return departmentMapper.toResponseDto(updated);
	}

// ================= DELETE / DEACTIVATE =================

	@Override
	public void deleteDepartment(Long id) {
		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found"));

		List<Employee> employee = employeeRepository.findByDepartment_id(id);

		if (!employee.isEmpty()) {
			throw new RuntimeException("Employees are still assigned to this department");
		}

		department.setActive(false);

		departmentRepository.save(department);

	}

// ================= BULK SALARY RAISE =================

	@Override
	@Transactional
	public void raiseSalaryForDepartment(Long departmentId, double percentage) {

		Department department = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new RuntimeException("Department not found"));

		List<Employee> employees = department.getEmployee();

		for (Employee employee : employees) {

			double currentSalary = employee.getSalary();

			double increasedSalary = currentSalary + (currentSalary * percentage / 100);

			employee.setSalary(increasedSalary);
		}

	}

	// ================= ANALYTICS =================

	@Override
	public DepartmentResponseDto getDepartmentStats(Long id) {

		Department department = departmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Department not found"));

		return departmentMapper.toResponseDto(department);
	}

//===================Search by name and Location ==========================
	// DepartmentServiceImpl.java mein niche add karein
	public Page<DepartmentResponseDto> searchDepartments(DepartmentName name, String location, Pageable pageable) {
	    return departmentRepository.findByNameAndLocationContaining(name, location, pageable)
	            .map(departmentMapper::toResponseDto);
	}

}
