package com.nexusHR.mapper;

import com.nexusHR.dto.DepartmentRequestDto;
import com.nexusHR.dto.DepartmentResponseDto;
import com.nexusHR.entity.Department;
import com.nexusHR.entity.enums.DepartmentName; // Sahi import check karein
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {

    private final ModelMapper modelMapper;

    public DepartmentResponseDto toResponseDto(Department department) {
        DepartmentResponseDto dto = modelMapper.map(department, DepartmentResponseDto.class);
        if (department.getEmployee() != null) {
            dto.setTotalEmployee(department.getEmployee().size());
        }
        return dto;
    }

    public Department toEntity(DepartmentRequestDto dto) {
        // 1. Pehle basic fields map karo
        Department department = modelMapper.map(dto, Department.class);

        // 2. Enum handling with Null Check
        if (dto.getName() != null) {
            try {
                // toUpperCase() tabhi chalega jab name null nahi hoga
                String nameStr = dto.getName().toString().trim().toUpperCase();
                department.setName(DepartmentName.valueOf(nameStr));
            } catch (IllegalArgumentException e) {
                // Agar Enum match nahi hua toh
                throw new RuntimeException("Invalid Department Name. Use HR, IT, DEVELOPMENT etc.");
            }
        }
        return department;
    }

    public void updateEntity(DepartmentRequestDto dto, Department department) {
        modelMapper.map(dto, department);
        
        if (dto.getName() != null) {
            try {
                String nameStr = dto.getName().toString().trim().toUpperCase();
                department.setName(DepartmentName.valueOf(nameStr));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid Department Name during update.");
            }
        }
    }
}