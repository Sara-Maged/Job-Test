package com.example.JobManagementScv.model;

import com.example.JobManagementScv.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobHistoryId;

    @OneToOne
    @JoinColumn(name = "employeeId", unique = true, nullable = false)
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "departmentId", unique = true, nullable = false)
    private Department department;

    @OneToOne
    @JoinColumn(name = "jobId", unique = true, nullable = false)
    private Job job;

    private Instant startDate;
    private Instant endDate;
    private Language language;
}
