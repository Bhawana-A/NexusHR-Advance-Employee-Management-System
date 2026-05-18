package com.nexusHR.dto;

import java.time.LocalDate;
import java.util.List;

import com.nexusHR.entity.enums.ProjectStatus;

import lombok.Data;

@Data
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
    
    private String description;
    private ProjectStatus status;
    
    // Project mein kaam karne wale employees ke naam dikhane ke liye
    private List<String> employeeNames; 
    
    private int totalMembers;
    
    
}