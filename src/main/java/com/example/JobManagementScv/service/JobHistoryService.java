package com.example.JobManagementScv.service;

import com.example.JobManagementScv.model.JobHistory;
import com.example.JobManagementScv.repository.JobHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobHistoryService {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    public List<JobHistory> getAllJobHistories() {
        return jobHistoryRepository.findAll();
    }

    public Optional<JobHistory> getJobHistoryById(Long id) {
        return jobHistoryRepository.findById(id);
    }

    public JobHistory createJobHistory(JobHistory jobHistory) {
        return jobHistoryRepository.save(jobHistory);
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
