package com.example.JobManagementScv.service;

import com.example.JobManagementScv.model.Country;
import com.example.JobManagementScv.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    public Optional<Country> updateCountry(Long id, Country country) {
        return countryRepository.findById(id)
                .map(existingCountry -> {
                    existingCountry.setCountryName(country.getCountryName());
                    existingCountry.setRegion(country.getRegion());
                    return countryRepository.save(existingCountry);
                });
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}

