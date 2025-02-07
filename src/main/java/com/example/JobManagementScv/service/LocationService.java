package com.example.JobManagementScv.service;

import com.example.JobManagementScv.model.Location;
import com.example.JobManagementScv.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

//    TODO: Use Mapper instead
    public Optional<Location> updateLocation(Long id, Location location) {
        return locationRepository.findById(id)
                .map(existingLocation -> {
                    existingLocation.setStreetAddress(location.getStreetAddress());
                    existingLocation.setPostalCode(location.getPostalCode());
                    existingLocation.setCity(location.getCity());
                    existingLocation.setStateProvince(location.getStateProvince());
                    existingLocation.setCountry(location.getCountry());
                    return locationRepository.save(existingLocation);
                });
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
