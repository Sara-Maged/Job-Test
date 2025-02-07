package com.example.JobManagementScv.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Instant hireDate;
    private Long salary;
    private Long commissionPct;

    @ManyToOne // Many employees to one department
    @JoinColumn(name = "departmentId")
    private Department department;

    @ManyToOne  // Many employees to one manager
    @JoinColumn(name = "managerId", referencedColumnName = "employeeId", nullable = true)
    @JsonBackReference // Prevents infinite recursion
    private Employee manager;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Allows proper serialization
    private List<Employee> managedEmployees = new ArrayList<>();
}
