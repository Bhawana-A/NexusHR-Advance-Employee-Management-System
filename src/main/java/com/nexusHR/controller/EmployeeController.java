package com.nexusHR.controller;

import com.nexusHR.dto.EmployeeRequestDto;
import com.nexusHR.dto.EmployeeResponseDto;
import com.nexusHR.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // 1. Onboarding - Create Employee
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto dto) {
        return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
    }

    // 2. Get All with Pagination & Sorting
    // Example: /api/v1/employees?page=0&size=10&sort=firstname,asc
    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDto>> getAllEmployees(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }

    // 3. Get Single Employee
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // 4. Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable Long id, 
            @Valid @RequestBody EmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }

    // 5. Soft Delete (Deactivate)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Transfer (Department Change)
    // PATCH /api/v1/employees/{id}/transfer?newDeptId=2
    @PatchMapping("/{id}/transfer")
    public ResponseEntity<EmployeeResponseDto> transferEmployee(
            @PathVariable Long id, 
            @RequestParam Long newDeptId) {
        return ResponseEntity.ok(employeeService.transferEmployee(id, newDeptId));
    }

    // 7. Promotion (Salary & Designation Change)
    // PATCH /api/v1/employees/{id}/promote
    @PatchMapping("/{id}/promote")
    public ResponseEntity<EmployeeResponseDto> promoteEmployee(
            @PathVariable Long id,
            @RequestParam String designation,
            @RequestParam double salary) {
        return ResponseEntity.ok(employeeService.promoteEmployee(id, designation, salary));
    }
}