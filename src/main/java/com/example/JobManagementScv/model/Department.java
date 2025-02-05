package com.example.JobManagementScv.model;

import javax.persistence.*;

@Entity
@Table
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    private String departmentName;

    @OneToOne // One department to one location
    @JoinColumn(name = "locationId")
    private Location location;
}
