package com.abes.warrentyWave.repository;

import com.abes.warrentyWave.entity.WarrantyClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarrentyClaimRepository extends JpaRepository<WarrantyClaim, Long> {
    List<WarrantyClaim> findByStatus(String status);
}
