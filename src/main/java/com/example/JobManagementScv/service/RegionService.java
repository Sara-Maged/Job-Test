package com.example.JobManagementScv.service;

import com.example.JobManagementScv.model.Region;
import com.example.JobManagementScv.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(Long regionId) {
        return regionRepository.findById(regionId);
    }

    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    public void deleteRegion(Long regionId) {
        regionRepository.deleteById(regionId);
    }
}
