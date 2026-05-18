package com.nexusHR.mapper;

import com.nexusHR.dto.LeaveRequestDto;
import com.nexusHR.dto.LeaveResponseDto;
import com.nexusHR.entity.LeaveRequest;
import org.springframework.stereotype.Component;

@Component
public class LeaveRequestMapper {

    public LeaveResponseDto toResponseDto(LeaveRequest entity) {
        if (entity == null) return null;
        LeaveResponseDto dto = new LeaveResponseDto();
        dto.setId(entity.getId());
        dto.setLeaveType(entity.getLeaveType());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setReason(entity.getReason());
        dto.setStatus(entity.getStatus());
        
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
            dto.setEmployeeName(entity.getEmployee().getFirstname() + " " + entity.getEmployee().getLastname());
        }
        return dto;
    }

    public LeaveRequest toEntity(LeaveRequestDto dto) {
        if (dto == null) return null;
        LeaveRequest entity = new LeaveRequest();
        entity.setLeaveType(dto.getLeaveType());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setReason(dto.getReason());
        return entity;
    }
}