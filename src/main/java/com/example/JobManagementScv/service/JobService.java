package com.example.JobManagementScv.service;

import com.example.JobManagementScv.dto.JobDTO;
import com.example.JobManagementScv.dto.TaskDTO;
import com.example.JobManagementScv.model.Job;
import com.example.JobManagementScv.model.Task;
import com.example.JobManagementScv.repository.JobRepository;
import com.example.JobManagementScv.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final TaskRepository taskRepository;
    private final JobHistoryService jobHistoryService;

    public JobService(JobRepository jobRepository, TaskRepository taskRepository, JobHistoryService jobHistoryService) {
        this.jobRepository = jobRepository;
        this.taskRepository = taskRepository;
        this.jobHistoryService = jobHistoryService;
    }

    public JobDTO getJobById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        return job.map(this::convertToDto).orElse(null);
    }

    public Job createJob(Job job) {
        Job createdJob = jobRepository.save(job);
        jobHistoryService.createJobHistoryByJob(job);
        return createdJob;
    }

    public JobDTO updateJob(Long id, Job job) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        existingJob.setJobTitle(job.getJobTitle());
        existingJob.setMinSalary(job.getMinSalary());
        existingJob.setMaxSalary(job.getMaxSalary());
        existingJob.setEmployee(job.getEmployee());
        existingJob.setTasks(job.getTasks());

        Job updatedJob = jobRepository.save(existingJob);
        jobHistoryService.createJobHistoryByJob(updatedJob);
        return convertToDto(updatedJob);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Transactional // for ManyToMany relationships
    public void assignTaskToJob(Long jobId, Long taskId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        job.getTasks().add(task);
        task.getJobs().add(job);

        jobRepository.save(job); // Only need to save one side (usually the "owning" side)
    }

    @Transactional
    public void removeTaskFromJob(Long jobId, Long taskId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        job.getTasks().remove(task);
        task.getJobs().remove(job);

        jobRepository.save(job); // Save the owning side
    }

    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(job.getJobId());
        jobDTO.setJobTitle(job.getJobTitle());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());

        if (job.getTasks() != null) {
            Set<TaskDTO> taskDTOs = new HashSet<>();
            for (Task task : job.getTasks()) {
                taskDTOs.add(TaskService.convertToDto(task));
            }
            jobDTO.setTasks(taskDTOs);
        }
        return jobDTO;
    }
}
