package com.example.JobManagementScv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JobManagementSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobManagementSvcApplication.class, args);
    }

}
