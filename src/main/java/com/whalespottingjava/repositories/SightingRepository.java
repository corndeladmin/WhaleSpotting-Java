package com.whalespottingjava.repositories;

import com.whalespottingjava.models.database.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {
    @Query(value = "SELECT * FROM whale_sighting u WHERE u.approved = true", nativeQuery = true)
    List<Sighting> findALlApprovedSightings();
    public List<Sighting> findByApprovedTrue();

}
