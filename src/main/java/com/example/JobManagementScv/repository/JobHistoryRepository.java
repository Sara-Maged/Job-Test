package com.example.JobManagementScv.repository;

import com.example.JobManagementScv.model.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
    List<JobHistory> findByEmployee_EmployeeId(Long employeeId);
}
