package com.nexusHR.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.nexusHR.dto.ProjectRequestDto;
import com.nexusHR.dto.ProjectResponseDto;
import com.nexusHR.entity.Project;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

	private final ModelMapper modelMapper;
	
	public ProjectResponseDto toResponseDto(Project project) {
        ProjectResponseDto dto = modelMapper.map(project, ProjectResponseDto.class);
        
        if (project.getEmployee() != null) {
            dto.setTotalMembers(project.getEmployee().size()); // Yahan count set ho raha hai
            dto.setEmployeeNames(project.getEmployee().stream()
                    .map(emp -> emp.getFirstname() + " " + emp.getLastname())
                    .collect(Collectors.toList()));
        } else {
            dto.setTotalMembers(0);
        }
        return dto;
    }

    public Project toEntity(ProjectRequestDto dto) {
        return modelMapper.map(dto, Project.class);
    }
    
}

