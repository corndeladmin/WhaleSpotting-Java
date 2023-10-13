package com.whalespottingjava.repositories;

import com.whalespottingjava.models.database.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {
    public List<Sighting> findByApprovedTrue();
}
