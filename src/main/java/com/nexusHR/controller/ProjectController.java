package com.nexusHR.controller;

import com.nexusHR.dto.ProjectRequestDto;
import com.nexusHR.dto.ProjectResponseDto;
import com.nexusHR.entity.enums.ProjectStatus;
import com.nexusHR.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // 1. Create Project
    // POST /api/v1/projects
    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto dto) {
        ProjectResponseDto createdProject = projectService.createProject(dto);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // 2. List All Projects
    // GET /api/v1/projects
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // 3. Get Project By ID
    // GET /api/v1/projects/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    // 4. Update Project Details
    // PUT /api/v1/projects/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(
            @PathVariable Long id, 
            @Valid @RequestBody ProjectRequestDto dto) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    // 5. Assign Team to Project (Advanced Many-to-Many)
    // POST /api/v1/projects/{projectId}/assign
    // Body Example: { "1": "Lead", "2": "Developer" } (Key is Employee ID)
    @PostMapping("/{projectId}/assign")
    public ResponseEntity<String> assignTeam(
            @PathVariable Long projectId, 
            @RequestBody Map<Long, String> employeeRoleMap) {
        projectService.assignTeamToProject(projectId, employeeRoleMap);
        return ResponseEntity.ok("Team assigned to project successfully.");
    }

    // 6. Remove Employee from Project
    // DELETE /api/v1/projects/{projectId}/employees/{employeeId}
    @DeleteMapping("/{projectId}/employees/{employeeId}")
    public ResponseEntity<String> removeEmployee(
            @PathVariable Long projectId, 
            @PathVariable Long employeeId) {
        projectService.removeEmployeeFromProject(projectId, employeeId);
        return ResponseEntity.ok("Employee removed from project successfully.");
    }

    // 7. Get Project Timeline/Backlog
    // GET /api/v1/projects/{id}/backlog
    @GetMapping("/{id}/backlog")
    public ResponseEntity<List<String>> getProjectTimeline(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectTimeline(id));
    }

    // 8. Update Project Status (Specialized Update)
    // PATCH /api/v1/projects/{id}/status
    @PatchMapping("/{id}/status")
    public ResponseEntity<ProjectResponseDto> updateStatus(
            @PathVariable Long id, 
            @RequestParam ProjectStatus status) {
        return ResponseEntity.ok(projectService.updateProjectStatus(id, status));
    }
}