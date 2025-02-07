package com.example.JobManagementScv.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "jobs") // Exclude the jobs field
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String title;
    private String description;

    @ManyToMany // Many tasks to many jobs
    @JoinTable(
            name = "JobTask", // Name of the junction table
            joinColumns = @JoinColumn(name = "taskId"), // Foreign key for Task
            inverseJoinColumns = @JoinColumn(name = "jobId") // Foreign key for Job
    )
    private Set<Job> jobs = new HashSet<>();
}
