package com.nexusHR.service;

import com.nexusHR.dto.ProjectRequestDto;
import com.nexusHR.dto.ProjectResponseDto;
import com.nexusHR.entity.enums.ProjectStatus;
import java.util.List;
import java.util.Map;

public interface ProjectService {

    // --- Core Project Management ---
    // Req: POST /api/v1/projects (Define start/end date, client name)
    ProjectResponseDto createProject(ProjectRequestDto dto);
    
    ProjectResponseDto updateProject(Long id, ProjectRequestDto dto);
    
    ProjectResponseDto getProjectById(Long id);
    
    List<ProjectResponseDto> getAllProjects();

    // --- Team & Resource Management (Advanced) ---
    
    // Req: POST /api/v1/projects/{projectId}/assign 
    // Logic: Accepts list of Employee IDs and their roles (e.g., Lead, Developer)
    void assignTeamToProject(Long projectId, Map<Long, String> employeeRoleMap);
    
    // Req: DELETE /api/v1/projects/{projectId}/employees/{employeeId}
    void removeEmployeeFromProject(Long projectId, Long employeeId);

    // --- Timeline & Backlog ---
    
    // Req: GET /api/v1/projects/{id}/backlog (List of milestones)
    List<String> getProjectTimeline(Long projectId);

    // --- Specialized Updates ---
    ProjectResponseDto updateProjectStatus(Long id, ProjectStatus status);
}