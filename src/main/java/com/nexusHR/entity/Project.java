package com.nexusHR.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nexusHR.entity.enums.ProjectStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(length = 500)
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.PLANNED;
    
    private boolean active=true;
    
    @ManyToMany(mappedBy = "projects") // Employee table is the owner
    @JsonBackReference                 // Stop infinite loop here
    private List<Employee> employee;
}