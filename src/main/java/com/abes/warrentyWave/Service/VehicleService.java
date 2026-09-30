package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.dto.VehicleRequestDTO;
import com.abes.warrentyWave.dto.VehicleResponseDTO;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    VehicleResponseDTO registerVehicle(VehicleRequestDTO vehicleRequest);

    List<VehicleResponseDTO> getAllVehicles();

    VehicleResponseDTO getVehicleById(Long id);

    Optional<VehicleResponseDTO> getVehicleByVin(String vin);

    List<VehicleResponseDTO> getVehiclesByCustomerId(Long customerId);

    void deleteVehicle(Long id);
}
