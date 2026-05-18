package com.nexusHR.entity;

import java.time.LocalDate;
import com.nexusHR.entity.enums.LeaveType;
import com.nexusHR.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="emp_id")
    private Employee employee;
}