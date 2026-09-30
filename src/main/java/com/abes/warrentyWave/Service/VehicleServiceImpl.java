package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.entity.Vehicle;
import com.abes.warrentyWave.repository.VehicleRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepositoy vehicleRepositoy;

    @Autowired
    public VehicleServiceImpl(VehicleRepositoy vehicleRepositoy) {
        this.vehicleRepositoy = vehicleRepositoy;
    }

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepositoy.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepositoy.findAll();
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepositoy.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    @Override
    public Optional<Vehicle> getVehicleByVin(String vin) {
        return vehicleRepositoy.findByVin(vin);
    }

    @Override
    public List<Vehicle> getVehiclesByCustomerId(Long customerId) {
        return vehicleRepositoy.findByCustomerId(customerId);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepositoy.deleteById(id);
    }
}
