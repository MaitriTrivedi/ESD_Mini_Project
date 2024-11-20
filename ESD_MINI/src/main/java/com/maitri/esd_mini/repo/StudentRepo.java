package com.maitri.esd_mini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.maitri.esd_mini.entity.Students;
public interface StudentRepo extends JpaRepository<Students, Long> {
    Optional<Students> findByEmail(String email);
}
