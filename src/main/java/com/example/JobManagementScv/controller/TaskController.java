package com.example.JobManagementScv.controller;

import com.example.JobManagementScv.model.Employee;
import com.example.JobManagementScv.model.Job;
import com.example.JobManagementScv.model.Task;
import com.example.JobManagementScv.service.TaskService;
import com.example.JobManagementScv.util.Utilities;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task Management", description = "Endpoints for managing tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByTaskId(@PathVariable Long id) {
        List<Employee> employees = taskService.getEmployeesByTaskId(id);
        return Utilities.handleListResponse(employees);
    }

    @GetMapping("/{id}/jobs")
    public ResponseEntity<List<Job>> getJobsByTaskId(@PathVariable Long id) {
        List<Job> jobs = taskService.getJobsByTaskId(id);
        return Utilities.handleListResponse(jobs);
    }
}
