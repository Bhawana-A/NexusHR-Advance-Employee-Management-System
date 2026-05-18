package com.nexusHR.controller;

import com.nexusHR.dto.DepartmentRequestDto;
import com.nexusHR.dto.DepartmentResponseDto;
import com.nexusHR.entity.enums.DepartmentName;
import com.nexusHR.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // 1. Onboarding - Create Department
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(@Valid @RequestBody DepartmentRequestDto dto) {
        return new ResponseEntity<>(departmentService.createDepartment(dto), HttpStatus.CREATED);
    }

    // 2. List All with Pagination OR Search
    @GetMapping
    public ResponseEntity<Page<DepartmentResponseDto>> getDepartments(
            @RequestParam(required = false) DepartmentName name,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        // Agar name ya location aa raha hai toh search call karo
        if (name != null || (location != null && !location.isEmpty())) {
            // Note: searchDepartments method interface mein add hona chahiye
            return ResponseEntity.ok(departmentService.searchDepartments(name, location, pageable));
        }

        return ResponseEntity.ok(departmentService.getAllDepartments(pageable));
    }

    // 3. Get Specific Department
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    // 4. Update Department
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> update(@PathVariable Long id, @Valid @RequestBody DepartmentRequestDto dto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    // 5. Bulk Salary Raise (Advanced Concept)
    @PutMapping("/{id}/raise")
    public ResponseEntity<String> raiseSalary(@PathVariable Long id, @RequestParam double percentage) {
        departmentService.raiseSalaryForDepartment(id, percentage);
        return ResponseEntity.ok("Salary raised by " + percentage + "% for all employees in department ID: " + id);
    }

    // 6. Analytics
    @GetMapping("/{id}/stats")
    public ResponseEntity<DepartmentResponseDto> getStats(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentStats(id));
    }

    // 7. Deactivate (Soft Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department deactivated successfully.");
    }
}