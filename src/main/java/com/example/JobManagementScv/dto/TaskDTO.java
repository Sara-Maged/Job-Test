package com.example.JobManagementScv.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TaskDTO {
    private Long taskId;
    private String title;
    private String description;

    private Set<JobDTO> jobs;
}
