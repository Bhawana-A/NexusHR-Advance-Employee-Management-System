package com.nexusHR.dto;

import java.time.LocalDate;
import com.nexusHR.entity.enums.LeaveType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LeaveRequestDto {
    @NotNull(message = "Leave type is required")
    private LeaveType leaveType;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotBlank(message = "Reason is required")
    private String reason;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;
}