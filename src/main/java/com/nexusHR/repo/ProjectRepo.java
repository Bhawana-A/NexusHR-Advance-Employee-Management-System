package com.nexusHR.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexusHR.entity.Project;
import com.nexusHR.entity.enums.ProjectStatus;


@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>{

	List<Project> findByStatus(ProjectStatus status);
	List<Project> findByClientNameContainingIgnoreCase(String clientName);
}
