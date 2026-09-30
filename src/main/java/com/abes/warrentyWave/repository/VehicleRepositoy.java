package com.abes.warrentyWave.repository;

import com.abes.warrentyWave.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepositoy extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByVin(String vin);
    List<Vehicle> findByCustomerId(Long customerId);
}
