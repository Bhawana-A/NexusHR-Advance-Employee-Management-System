package com.nexusHR.dto;

import java.time.LocalDate;

import com.nexusHR.entity.enums.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectRequestDto {

    @NotBlank(message = "Project name cannot be empty")
    private String name;

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "end date is required")
    private LocalDate endDate;
    
    @NotBlank(message = "Project description is required")
    @Size(min = 10, max = 500, message = "Description should be between 10 to 500 characters")
    private String description;
    
    @NotNull(message = "Project status is required")
    private ProjectStatus status;
    
}

