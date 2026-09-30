package com.abes.warrentyWave.controller;

import com.abes.warrentyWave.Service.VehicleService;
import com.abes.warrentyWave.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle registerVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.registerVehicle(vehicle);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("/vin/{vin}")
    public Optional<Vehicle> getVehicleByVin(@PathVariable String vin) {
        return vehicleService.getVehicleByVin(vin);
    }

    @GetMapping("/customer/{customerId}")
    public List<Vehicle> getVehiclesByCustomerId(@PathVariable Long customerId) {
        return vehicleService.getVehiclesByCustomerId(customerId);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
