package com.whalespottingjava.repositories;

import com.whalespottingjava.models.database.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, Long> {

    @Query(value = "SELECT * FROM whale_sighting u WHERE u.approved = true", nativeQuery = true)
    List<Sighting> findALlApprovedSightings();


}
