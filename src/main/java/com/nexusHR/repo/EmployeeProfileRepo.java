package com.nexusHR.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexusHR.entity.EmployeeProfile;


@Repository
public interface EmployeeProfileRepo extends JpaRepository<EmployeeProfile, Long>{

	Optional<EmployeeProfile> findByEmployee_id(Long employeeId);
	
}
