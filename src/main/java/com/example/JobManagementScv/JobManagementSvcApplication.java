package com.example.JobManagementScv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.JobManagementScv.repository")
@EnableJpaRepositories
@EntityScan(basePackages = "com.example.JobManagementScv.model")
public class JobManagementSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobManagementSvcApplication.class, args);
    }

}
