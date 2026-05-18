package com.nexusHR.dto;

import java.time.LocalDate;
import com.nexusHR.entity.enums.LeaveType;
import com.nexusHR.entity.enums.Status;
import lombok.Data;

@Data
public class LeaveResponseDto {
    private Long id;    
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private Status status;
    private Long employeeId; // Entity nahi, sirf ID
    private String employeeName;
}