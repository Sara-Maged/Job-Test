package com.example.JobManagementScv.model;

import javax.persistence.*;

@Entity
@Table
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;

    @OneToOne // Still think it should be Many to one -- Many locations to one country
    @JoinColumn(name = "countryId")
    private Country country;
}
