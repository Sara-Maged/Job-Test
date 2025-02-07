package com.example.JobManagementScv.service;

import com.example.JobManagementScv.model.Employee;
import com.example.JobManagementScv.model.Job;
import com.example.JobManagementScv.model.JobHistory;
import com.example.JobManagementScv.repository.EmployeeRepository;
import com.example.JobManagementScv.repository.JobHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class JobHistoryService {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<JobHistory> getAllJobHistories() {
        return jobHistoryRepository.findAll();
    }

    public Optional<JobHistory> getJobHistoryById(Long id) {
        return jobHistoryRepository.findById(id);
    }

    public JobHistory createJobHistory(JobHistory jobHistory) {
        return jobHistoryRepository.save(jobHistory);
    }

    public void createJobHistoryByJob(Job job) {
        if (job.getEmployee() != null) {
            JobHistory jobHistory = new JobHistory();

            // ***KEY CHANGE: Set only the IDs***
            Employee employee = employeeRepository.findById(job.getEmployee().getEmployeeId()).orElse(null);
            if (employee != null) {
                jobHistory.setEmployee(employee); // Set the Employee entity
                jobHistory.setDepartment(employee.getDepartment()); // Set the department

                jobHistory.setJob(job); // Set the Job entity
                jobHistory.setStartDate(Instant.now());
                jobHistoryRepository.save(jobHistory);
            } else {
                // Handle the case where the employee is not found.
                throw new EntityNotFoundException("Employee not found with ID: " + job.getEmployee().getEmployeeId());
            }

        }
    }

    public Optional<JobHistory> updateJobHistory(Long id, JobHistory jobHistory) {
        return jobHistoryRepository.findById(id)
                .map(existingJobHistory -> {
                    existingJobHistory.setEmployee(jobHistory.getEmployee());
                    existingJobHistory.setDepartment(jobHistory.getDepartment());
                    existingJobHistory.setJob(jobHistory.getJob());
                    existingJobHistory.setStartDate(jobHistory.getStartDate());
                    existingJobHistory.setEndDate(jobHistory.getEndDate());
                    existingJobHistory.setLanguage(jobHistory.getLanguage());
                    return jobHistoryRepository.save(existingJobHistory);
                });
    }

    public void deleteJobHistory(Long id) {
        jobHistoryRepository.deleteById(id);
    }
}
