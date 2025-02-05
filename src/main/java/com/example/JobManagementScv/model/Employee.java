package com.example.JobManagementScv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "Employee")
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
//    private Long managerId;

    @ManyToOne // Many employees to one department
    @JoinColumn(name = "departmentId")
    private Department department;

    @ManyToOne  // Many employees to one manager
    @JoinColumn(name = "managerId")
    @ToString.Exclude // to prevent recursion
    private Employee manager;

    // collection for employees managed by this employee
//    @OneToMany(mappedBy = "manager")
//    private List<Employee> managedEmployees;
}
