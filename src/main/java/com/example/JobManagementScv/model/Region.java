package com.example.JobManagementScv.model;

import javax.persistence.*;

@Entity
@Table
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;
    private String regionName;
}
