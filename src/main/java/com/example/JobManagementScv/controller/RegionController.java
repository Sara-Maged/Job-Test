package com.example.JobManagementScv.controller;

import com.example.JobManagementScv.model.Region;
import com.example.JobManagementScv.service.RegionClient;
import com.example.JobManagementScv.service.RegionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/regions")
@Tag(name = "Region Management", description = "Endpoints for managing regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionClient regionClient;

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionService.getAllRegions();
        System.out.println("Regions fetched: " + regions.size());
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long regionId) {
        Optional<Region> region = regionService.getRegionById(regionId);
        return region.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        Region savedRegion = regionService.createRegion(region);
        return new ResponseEntity<>(savedRegion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{regionId}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long regionId) {
        regionService.deleteRegion(regionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/client")
    public ResponseEntity<List<Region>> getRegionsFromJobManagement() {
        return ResponseEntity.ok(regionClient.fetchRegions());
    }
}
