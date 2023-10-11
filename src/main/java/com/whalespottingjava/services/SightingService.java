package com.whalespottingjava.services;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.repositories.SightingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.sql.Date;
import java.util.List;

@Service
public class SightingService {

    private final SightingRepository sightingRepository;

    @Autowired
    public SightingService(SightingRepository sightingRepository) {this.sightingRepository = sightingRepository;}

    public List<Sighting> getAllSightings()
    {
        return sightingRepository.findAll();
    }

    public List<Sighting> getAllApprovedSightings() {
        return this.sightingRepository.findAll().stream()
                .filter(Sighting::getApproved)
                .toList();
    }
}
