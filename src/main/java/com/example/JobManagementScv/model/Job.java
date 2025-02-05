package com.example.JobManagementScv.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;
    private String jobTitle;
    private Long minSalary;
    private Long maxSalary;

    @ManyToMany(mappedBy = "jobs") // 'jobs' field in Task
    private Set<Task> tasks = new HashSet<>();

}
