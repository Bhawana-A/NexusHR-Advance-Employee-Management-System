package com.nexusHR.service.impl;

import com.nexusHR.dto.*;
import com.nexusHR.entity.*;
import com.nexusHR.entity.enums.*;
import com.nexusHR.exception.ResourceNotFoundException;
import com.nexusHR.mapper.LeaveRequestMapper;
import com.nexusHR.repo.*;
import com.nexusHR.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepo leaveRepo;
    private final EmployeeRepo employeeRepo;
    private final LeaveRequestMapper leaveMapper;

    @Override
    @Transactional
    public LeaveResponseDto applyForLeave(LeaveRequestDto dto) {
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LeaveRequest leave = leaveMapper.toEntity(dto);
        leave.setEmployee(employee);
        leave.setStatus(Status.PENDING); // Naya request hamesha Pending
        
        LeaveRequest saved = leaveRepo.save(leave);
        return leaveMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public LeaveResponseDto updateLeaveStatus(Long leaveId, Status status) {
        LeaveRequest leave = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave Request not found"));
        leave.setStatus(status);
        return leaveMapper.toResponseDto(leaveRepo.save(leave));
    }

    @Override
    public int getRemainingLeaveBalance(Long employeeId) {
        // Assume TOTAL = 24
        List<LeaveRequest> approved = leaveRepo.findByEmployeeIdAndStatus(employeeId, Status.APPROVED);
        long taken = approved.stream()
                .mapToLong(l -> ChronoUnit.DAYS.between(l.getStartDate(), l.getEndDate()) + 1)
                .sum();
        return 24 - (int) taken;
    }

    @Override
    public List<LeaveResponseDto> getEmployeeLeaveHistory(Long employeeId) {
        return leaveRepo.findByEmployeeId(employeeId).stream()
                .map(leaveMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}