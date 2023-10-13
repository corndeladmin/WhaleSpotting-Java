package com.whalespottingjava.repositories;

import com.whalespottingjava.models.database.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> 4b23ab9 (Code Cleanup)
import java.util.List;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {
<<<<<<< HEAD
    public List<Sighting> findByApprovedTrue();
=======
    @Query(value = "SELECT * FROM whale_sighting u WHERE u.approved = true", nativeQuery = true)
    List<Sighting> findALlApprovedSightings();
>>>>>>> 4b23ab9 (Code Cleanup)
}
