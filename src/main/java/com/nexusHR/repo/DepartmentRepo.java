package com.nexusHR.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexusHR.dto.DepartmentResponseDto;
import com.nexusHR.entity.Department;
import com.nexusHR.entity.enums.DepartmentName;


@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    // Return type Department hona chahiye, DTO nahi
    Page<Department> findByNameAndLocationContaining(DepartmentName name, String location, Pageable pageable);
}
