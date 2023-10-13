package com.whalespottingjava.services;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.repositories.SightingRepository;
import groovy.transform.stc.ClosureSignatureHint;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SightingService {
    private final SightingRepository sightingRepository;

    @Autowired
    public SightingService(SightingRepository sightingRepository) {
        this.sightingRepository = sightingRepository;
    }

    public List<Sighting> getAllSightings() {
        return sightingRepository.findAll();
    }

    public void addSighting(Sighting sighting) 
    {
        sightingRepository.saveAndFlush(sighting);
    }

    public List<Sighting> getAllApprovedSightings() { // TODO: 12/10/2023 Returns raw JSON array, should be wrapped in object
        return this.sightingRepository.findByApprovedTrue();
    }

    public List<Sighting> getAllPendingSightings() {
        return this.sightingRepository.findByApprovedFalse();
    }
}
