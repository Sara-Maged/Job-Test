package com.example.JobManagementScv.service;

import com.example.JobManagementScv.dto.TaskDTO;
import com.example.JobManagementScv.model.*;
import com.example.JobManagementScv.repository.EmployeeRepository;
import com.example.JobManagementScv.repository.JobHistoryRepository;
import com.example.JobManagementScv.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.JobManagementScv.service.TaskService.convertToDto;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final JobHistoryRepository jobHistoryRepository;

    public EmployeeService(EmployeeRepository employeeRepository, JobRepository jobRepository, JobHistoryRepository jobHistoryRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.jobHistoryRepository = jobHistoryRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    public Page<Employee> getAllEmployees(Pageable pageable, String name, Long departmentId) {
        if (name != null && departmentId != null) {
            return employeeRepository.findByFirstNameContainingIgnoreCaseAndDepartment_DepartmentId(name, departmentId, pageable);
        } else if (name != null) {
            return employeeRepository.findByFirstNameContainingIgnoreCase(name, pageable);
        } else if (departmentId != null) {
            return employeeRepository.findByDepartment_DepartmentId(departmentId, pageable);
        } else {
            return employeeRepository.findAll(pageable);
        }
    }

    public List<Job> getJobsByEmployeeId(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
//            TODO: THROW EXCEPTION OR NOT?
            return List.of();
        }
        return jobRepository.findByEmployee_EmployeeId(employeeId);
    }

    public List<TaskDTO> getTasksByEmployeeId(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            return List.of();
        }

        List<Job> jobs = jobRepository.findByEmployee_EmployeeId(employeeId);
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Job job : jobs) {
            for (Task task : job.getTasks()) {
                taskDTOs.add(convertToDto(task));
            }
        }
        return taskDTOs.stream().distinct().toList();
    }

    public List<Department> getDepartmentsByEmployeeId(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            return List.of(); // Or throw exception
        }

        List<JobHistory> jobHistories = jobHistoryRepository.findByEmployee_EmployeeId(employeeId);
        return jobHistories.stream().map(JobHistory::getDepartment).distinct().toList();
    }
}
