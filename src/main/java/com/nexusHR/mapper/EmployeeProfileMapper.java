package com.nexusHR.mapper;

import com.nexusHR.dto.EmployeeProfileRequestDto;
import com.nexusHR.dto.EmployeeProfileResponseDto;
import com.nexusHR.entity.EmployeeProfile;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeProfileMapper {

    private final ModelMapper modelMapper;

    public EmployeeProfileResponseDto toResponseDto(EmployeeProfile profile) {
        EmployeeProfileResponseDto dto = modelMapper.map(profile, EmployeeProfileResponseDto.class);
        if (profile.getEmployee() != null) {
            dto.setEmployeeId(profile.getEmployee().getId());
        }
        return dto;
    }

    public EmployeeProfile toEntity(EmployeeProfileRequestDto dto) {
        return modelMapper.map(dto, EmployeeProfile.class);
    }
    
    public void updateEntity(EmployeeProfileRequestDto dto , EmployeeProfile profile) {
    	modelMapper.map(dto, profile);
    }
    
}