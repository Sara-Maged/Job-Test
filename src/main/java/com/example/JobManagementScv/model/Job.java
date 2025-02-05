package com.example.JobManagementScv.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Job")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;
    private String jobTitle;
    private Long minSalary;
    private Long maxSalary;

    @ManyToMany(mappedBy = "jobs") // 'jobs' field in Task
    private Set<Task> tasks = new HashSet<>();

    @ManyToOne // One employee to many jobs
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
