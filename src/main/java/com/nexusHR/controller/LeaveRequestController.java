package com.nexusHR.controller;

import com.nexusHR.dto.*;
import com.nexusHR.entity.enums.Status;
import com.nexusHR.service.LeaveRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping("/request")
    public ResponseEntity<LeaveResponseDto> applyLeave(@Valid @RequestBody LeaveRequestDto dto) {
        return new ResponseEntity<>(leaveRequestService.applyForLeave(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LeaveResponseDto> updateStatus(
            @PathVariable Long id, 
            @RequestParam Status status) { // Status Enum use karein
        return ResponseEntity.ok(leaveRequestService.updateLeaveStatus(id, status));
    }

    @GetMapping("/employee/{employeeId}/balance")
    public ResponseEntity<Integer> getLeaveBalance(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveRequestService.getRemainingLeaveBalance(employeeId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveResponseDto>> getHistory(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveRequestService.getEmployeeLeaveHistory(employeeId));
    }
}