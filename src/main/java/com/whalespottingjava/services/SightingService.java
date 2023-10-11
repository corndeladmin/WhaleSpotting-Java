package com.whalespottingjava.services;

import com.whalespottingjava.models.database.Sighting;
import com.whalespottingjava.repositories.SightingRepository;
import groovy.transform.stc.ClosureSignatureHint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SightingService {

    private final SightingRepository sightingRepository;

    @Autowired
    public SightingService(SightingRepository sightingRepository)
    {
        this.sightingRepository = sightingRepository;
    }

    public List<Sighting> getAllSightings()
    {
        return sightingRepository.findAll();
    }

}
