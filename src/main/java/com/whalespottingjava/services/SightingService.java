package com.whalespottingjava.services;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.repositories.SightingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SightingService {
    private final SightingRepository sightingRepository;

    @Autowired
    public SightingService(SightingRepository sightingRepository) {
        this.sightingRepository = sightingRepository;
    }

    public List<Sighting> getAllSightings() {
        return sightingRepository.findAll();
    }

    public List<Sighting> getAllApprovedSightings() { // TODO: 12/10/2023 Returns raw JSON array, should be wrapped in object
        return this.sightingRepository.findByApprovedTrue();
    }

    public void addBulkSightings(List<Sighting> sightings) {
        // Validate sightings (Use jakarta?)
        // Add to DB (look into bulk adding)
        this.sightingRepository.saveAll(sightings);
    }
}
