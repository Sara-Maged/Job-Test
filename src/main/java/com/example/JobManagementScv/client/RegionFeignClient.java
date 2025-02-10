package com.example.JobManagementScv.client;

import com.example.JobManagementScv.model.Region;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "JOBMANAGEMENTSVC")
public interface RegionFeignClient {

    @GetMapping("/job-api/regions")
    List<Region> getRegions();
}

