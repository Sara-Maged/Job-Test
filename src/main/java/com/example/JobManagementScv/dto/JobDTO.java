package com.example.JobManagementScv.dto;

import lombok.Data;

import java.util.Set;

@Data
public class JobDTO {
    private Long jobId;
    private String jobTitle;
    private Long minSalary;
    private Long maxSalary;

    private Set<TaskDTO> tasks;
}
