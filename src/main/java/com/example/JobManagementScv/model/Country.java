package com.example.JobManagementScv.model;

import javax.persistence.*;

@Entity
@Table
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;
    private String countryName;

    @OneToOne  // Still think it should be Many to one -- Many countries to one region
    @JoinColumn(name = "regionId") // Foreign key column
    private Region region;
}