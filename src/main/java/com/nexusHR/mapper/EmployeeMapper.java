package com.nexusHR.mapper;

import com.nexusHR.dto.EmployeeRequestDto;
import com.nexusHR.dto.EmployeeResponseDto;
import com.nexusHR.entity.Employee;
import com.nexusHR.entity.Project;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final ModelMapper modelMapper;

    public EmployeeResponseDto toResponseDto(Employee employee) {
        EmployeeResponseDto dto = modelMapper.map(employee, EmployeeResponseDto.class);
        
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
            // Agar department name enum hai toh .name() use karein
            if (employee.getDepartment().getName() != null) {
                dto.setDepartmentName(employee.getDepartment().getName().toString());
            }
        }

        if (employee.getProjects() != null) {
            Set<String> projectNames = employee.getProjects().stream()
                    .map(Project::getName)
                    .collect(Collectors.toSet());
            dto.setProject(projectNames);
        }
        return dto;
    }

    public Employee toEntity(EmployeeRequestDto dto) {
        // Direct mapping se bachne ke liye naya instance create karein
        return modelMapper.map(dto, Employee.class);
    }

    public void updateEntityFromDto(EmployeeRequestDto dto, Employee employee) {
        // Strategy ko STRICT karein taaki nested mapping na ho
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        // Naya TypeMap banayein ya existing use karein
        var typeMap = modelMapper.typeMap(EmployeeRequestDto.class, Employee.class);
        
        // Sabhi problematic fields ko skip karein
        typeMap.addMappings(m -> {
            m.skip(Employee::setId);
            m.skip(Employee::setDepartment);
            m.skip(Employee::setProjects);
        });

        modelMapper.map(dto, employee);
    }
}