package com.example.JobManagementScv.service;

import com.example.JobManagementScv.client.RegionFeignClient;
import com.example.JobManagementScv.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionClient {

    @Autowired
    private RegionFeignClient regionFeignClient;

    public List<Region> fetchRegions() {
        return regionFeignClient.getRegions();
    }
}
