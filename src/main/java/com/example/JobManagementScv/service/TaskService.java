package com.example.JobManagementScv.service;

import com.example.JobManagementScv.dto.JobDTO;
import com.example.JobManagementScv.dto.TaskDTO;
import com.example.JobManagementScv.model.Employee;
import com.example.JobManagementScv.model.Job;
import com.example.JobManagementScv.model.Task;
import com.example.JobManagementScv.repository.JobRepository;
import com.example.JobManagementScv.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final JobRepository jobRepository;

    public TaskService(TaskRepository taskRepository, JobRepository jobRepository) {
        this.taskRepository = taskRepository;
        this.jobRepository = jobRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task task) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(task.getTitle());
                    existingTask.setDescription(task.getDescription());
                    return taskRepository.save(existingTask);
                });
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Employee> getEmployeesByTaskId(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return List.of();
        }

        List<Job> jobs = jobRepository.findByTasks_TaskId(taskId);
        List<Employee> employees = new java.util.ArrayList<>();

        for (Job job : jobs) {
            if (job.getEmployee() != null) {
                employees.add(job.getEmployee());
            }
        }

        return employees.stream().distinct().toList();
    }

    public List<Job> getJobsByTaskId(Long taskId) {
        return jobRepository.findByTasks_TaskId(taskId);
    }

    public static TaskDTO convertToDto(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getTaskId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        // Handle jobs (lazy loading and avoid infinite recursion):
        if (task.getJobs() != null) {
            Set<JobDTO> jobDTOs = new HashSet<>();
            for (Job job : task.getJobs()) {
                JobDTO jobDTO = new JobDTO();
                jobDTO.setJobId(job.getJobId()); // Only map the ID to avoid recursion
                jobDTOs.add(jobDTO);
            }
            taskDTO.setJobs(jobDTOs);
        }
        return taskDTO;
    }
}