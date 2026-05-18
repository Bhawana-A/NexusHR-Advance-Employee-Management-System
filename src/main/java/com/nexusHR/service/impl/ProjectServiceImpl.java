package com.nexusHR.service.impl;

import com.nexusHR.dto.ProjectRequestDto;
import com.nexusHR.dto.ProjectResponseDto;
import com.nexusHR.entity.Employee;
import com.nexusHR.entity.Project;
import com.nexusHR.entity.enums.ProjectStatus;
import com.nexusHR.exception.ResourceNotFoundException;
import com.nexusHR.mapper.ProjectMapper;
import com.nexusHR.repo.EmployeeRepo;
import com.nexusHR.repo.ProjectRepo;
import com.nexusHR.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private final EmployeeRepo employeeRepo;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public ProjectResponseDto createProject(ProjectRequestDto dto) {
        Project project = projectMapper.toEntity(dto);
        // By default, project PLANNED status mein hoga agar DTO mein nahi hai
        if (project.getStatus() == null) {
            project.setStatus(ProjectStatus.PLANNED);
        }
        return projectMapper.toResponseDto(projectRepo.save(project));
    }

    @Override
    @Transactional
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto dto) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        
        project.setName(dto.getName());
        project.setClientName(dto.getClientName());
        project.setDescription(dto.getDescription());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        project.setStatus(dto.getStatus());
        
        return projectMapper.toResponseDto(projectRepo.save(project));
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        return projectRepo.findById(id)
                .map(projectMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        return projectRepo.findAll().stream()
                .map(projectMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // Req: POST /api/v1/projects/{projectId}/assign
    @Override
    @Transactional
    public void assignTeamToProject(Long projectId, Map<Long, String> employeeRoleMap) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        for (Long empId : employeeRoleMap.keySet()) {
            Employee employee = employeeRepo.findById(empId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found ID: " + empId));
            
            // Many-to-Many Relationship update
            if (!project.getEmployee().contains(employee)) {
                project.getEmployee().add(employee);
                // Note: Professional way mein hum yahan 'ProjectAssignment' 
                // entity use karte hain role (Lead/Dev) save karne ke liye.
            }
        }
        projectRepo.save(project);
    }

    // Req: DELETE /api/v1/projects/{projectId}/employees/{employeeId}
    @Override
    @Transactional
    public void removeEmployeeFromProject(Long projectId, Long employeeId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        project.getEmployee().remove(employee);
        projectRepo.save(project);
    }

    // Req: GET /api/v1/projects/{id}/backlog
    @Override
    public List<String> getProjectTimeline(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        
        // Example logic: Milestones return karna
        List<String> timeline = new ArrayList<>();
        timeline.add("Project Kickoff: " + project.getStartDate());
        timeline.add("Current Status: " + project.getStatus());
        if(project.getEndDate() != null) {
            timeline.add("Estimated Completion: " + project.getEndDate());
        }
        return timeline;
    }

    @Override
    @Transactional
    public ProjectResponseDto updateProjectStatus(Long id, ProjectStatus status) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        project.setStatus(status);
        return projectMapper.toResponseDto(projectRepo.save(project));
    }
}