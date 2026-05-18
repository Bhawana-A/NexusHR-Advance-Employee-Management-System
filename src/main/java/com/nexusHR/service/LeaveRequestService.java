package com.nexusHR.service;

import com.nexusHR.dto.LeaveRequestDto;
import com.nexusHR.dto.LeaveResponseDto;
import com.nexusHR.entity.enums.Status;
import java.util.List;

public interface LeaveRequestService {
    LeaveResponseDto applyForLeave(LeaveRequestDto dto);
    LeaveResponseDto updateLeaveStatus(Long leaveId, Status status);
    int getRemainingLeaveBalance(Long employeeId);
    List<LeaveResponseDto> getEmployeeLeaveHistory(Long employeeId);
}