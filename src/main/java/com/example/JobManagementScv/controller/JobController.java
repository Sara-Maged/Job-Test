package com.example.JobManagementScv.controller;

import com.example.JobManagementScv.dto.JobDTO;
import com.example.JobManagementScv.model.Job;
import com.example.JobManagementScv.service.JobService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@Tag(name = "Job Management", description = "Endpoints for managing jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        JobDTO jobDTO =  jobService.getJobById(id);
        if (jobDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jobDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable Long id, @RequestBody Job job) {
        JobDTO jobDTO = jobService.updateJob(id, job);
        if (jobDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jobDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{jobId}/tasks/{taskId}")
    public ResponseEntity<Void> assignTaskToJob(
            @PathVariable Long jobId,
            @PathVariable Long taskId) {

        jobService.assignTaskToJob(jobId, taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}/tasks/{taskId}")
    public ResponseEntity<Void> removeTaskFromJob(
            @PathVariable Long jobId,
            @PathVariable Long taskId) {

        jobService.removeTaskFromJob(jobId, taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
