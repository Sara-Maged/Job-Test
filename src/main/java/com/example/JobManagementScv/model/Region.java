package com.example.JobManagementScv.model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table
@Data
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;
    private String regionName;
}
