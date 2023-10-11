package com.whalespottingjava.repositories;

import com.whalespottingjava.models.database.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightingRepository extends JpaRepository<Sighting, Long> {

}
