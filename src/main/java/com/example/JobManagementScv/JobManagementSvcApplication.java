package com.example.JobManagementScv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.JobManagementScv.repository")
//@EnableJpaRepositories
//@EntityScan(basePackages = "com.example.JobManagementScv.model")
@EnableDiscoveryClient
@EnableEurekaServer
@EnableFeignClients
public class JobManagementSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobManagementSvcApplication.class, args);
    }

}
