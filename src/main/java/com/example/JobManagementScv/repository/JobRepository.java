package com.example.JobManagementScv.repository;

import com.example.JobManagementScv.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployee_EmployeeId(Long employeeId);
    List<Job> findByTasks_TaskId(Long taskId);
}
