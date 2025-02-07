package com.example.JobManagementScv.controller;

import com.example.JobManagementScv.model.JobHistory;
import com.example.JobManagementScv.service.JobHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobHistories")
@Tag(name = "Job History Management", description = "Endpoints for managing job history")
public class JobHistoryController {

    @Autowired
    private JobHistoryService jobHistoryService;

    @GetMapping
    public List<JobHistory> getAllJobHistories() {
        return jobHistoryService.getAllJobHistories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobHistory> getJobHistoryById(@PathVariable Long id) {
        return jobHistoryService.getJobHistoryById(id)
                .map(jobHistory -> ResponseEntity.ok(jobHistory))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<JobHistory> createJobHistory(@RequestBody JobHistory jobHistory) {
        JobHistory createdJobHistory = jobHistoryService.createJobHistory(jobHistory);
        return new ResponseEntity<>(createdJobHistory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobHistory> updateJobHistory(@PathVariable Long id, @RequestBody JobHistory jobHistory) {
        return jobHistoryService.updateJobHistory(id, jobHistory)
                .map(updatedJobHistory -> ResponseEntity.ok(updatedJobHistory))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobHistory(@PathVariable Long id) {
        jobHistoryService.deleteJobHistory(id);
        return ResponseEntity.noContent().build();
    }
}
