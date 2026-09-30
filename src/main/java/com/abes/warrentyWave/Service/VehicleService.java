package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle registerVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    Vehicle getVehicleById(Long id);

    Optional<Vehicle> getVehicleByVin(String vin);

    List<Vehicle> getVehiclesByCustomerId(Long customerId);

    void deleteVehicle(Long id);
}
