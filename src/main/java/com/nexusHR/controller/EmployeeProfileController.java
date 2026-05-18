package com.nexusHR.controller;

import com.nexusHR.dto.EmployeeProfileRequestDto;
import com.nexusHR.dto.EmployeeProfileResponseDto;
import com.nexusHR.service.EmployeeProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee-profiles")
@RequiredArgsConstructor
public class EmployeeProfileController {

    private final EmployeeProfileService profileService;

    // 1. Create Profile (Used during Onboarding)
    // POST /api/v1/employee-profiles
    @PostMapping
    public ResponseEntity<EmployeeProfileResponseDto> createProfile(
            @Valid @RequestBody EmployeeProfileRequestDto dto) {
        EmployeeProfileResponseDto created = profileService.createEmployeeProfile(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // 2. Get Profile by Employee ID
    // GET /api/v1/employee-profiles/employee/{employeeId}
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeProfileResponseDto> getProfileByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(profileService.getProfileByEmployeeId(employeeId));
    }

    // 3. Update Profile (Sensitive fields like Address/Contact)
    // PUT /api/v1/employee-profiles/employee/{employeeId}
    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeProfileResponseDto> updateProfile(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeProfileRequestDto dto) {
        return ResponseEntity.ok(profileService.updateProfile(employeeId, dto));
    }

    // 4. Delete Profile
    // DELETE /api/v1/employee-profiles/employee/{employeeId}
    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long employeeId) {
        profileService.deleteProfile(employeeId);
        return ResponseEntity.ok("Employee profile deleted successfully.");
    }
}