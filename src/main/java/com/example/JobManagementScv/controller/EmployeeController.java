package com.example.JobManagementScv.controller;

import com.example.JobManagementScv.dto.TaskDTO;
import com.example.JobManagementScv.model.Department;
import com.example.JobManagementScv.model.Employee;
import com.example.JobManagementScv.model.Job;
import com.example.JobManagementScv.model.Task;
import com.example.JobManagementScv.service.EmployeeService;
import com.example.JobManagementScv.util.Utilities;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Management", description = "Endpoints for managing employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(
            // If fields were too many consider switching to a POST
            @PageableDefault(size = 10, page = 0, sort = "employeeId") Pageable pageable,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "departmentId", required = false) Long departmentId
    ) {

        Page<Employee> employees = employeeService.getAllEmployees(pageable, name, departmentId);

        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}/jobs")
    public ResponseEntity<List<Job>> getJobsByEmployeeId(@PathVariable Long id) {
        List<Job> jobs = employeeService.getJobsByEmployeeId(id);
        return Utilities.handleListResponse(jobs);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksByEmployeeId(@PathVariable Long id) {
        List<TaskDTO> tasks = employeeService.getTasksByEmployeeId(id);
        return Utilities.handleListResponse(tasks);
    }

    @GetMapping("/{id}/departments")
    public ResponseEntity<List<Department>> getDepartmentsByEmployeeId(@PathVariable Long id) {
        List<Department> departments = employeeService.getDepartmentsByEmployeeId(id);
        return Utilities.handleListResponse(departments);
    }

}