package com.example.JobManagementScv.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Instant hireDate;
    private Long salary;
    private Long commissionPct;

    @ManyToOne // Many employees to one department
    @JoinColumn(name = "departmentId")
    private Department department;

    @ManyToOne // Many employees to one job
    @JoinColumn(name = "jobId")
    private Job job;
}
