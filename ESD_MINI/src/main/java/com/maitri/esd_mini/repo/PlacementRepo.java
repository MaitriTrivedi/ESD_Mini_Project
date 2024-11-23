package com.maitri.esd_mini.repo;

import com.maitri.esd_mini.entity.Placements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlacementRepo extends JpaRepository<Placements, Long> {
//    Optional<Students> findByEmail(String email);
}
