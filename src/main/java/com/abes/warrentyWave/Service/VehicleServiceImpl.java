package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.dto.VehicleRequestDTO;
import com.abes.warrentyWave.dto.VehicleResponseDTO;
import com.abes.warrentyWave.entity.Vehicle;
import com.abes.warrentyWave.mapper.EntityDtoMapper;
import com.abes.warrentyWave.repository.VehicleRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepositoy vehicleRepositoy;
    private final EntityDtoMapper mapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepositoy vehicleRepositoy, EntityDtoMapper mapper) {
        this.vehicleRepositoy = vehicleRepositoy;
        this.mapper = mapper;
    }

    @Override
    public VehicleResponseDTO registerVehicle(VehicleRequestDTO vehicleRequest) {
        if (vehicleRequest == null) {
            throw new IllegalArgumentException("Vehicle request cannot be null");
        }
        Vehicle vehicle = mapper.toEntity(vehicleRequest);
        Vehicle saved = vehicleRepositoy.save(vehicle);
        return mapper.toResponseDTO(saved);
    }

    @Override
    public List<VehicleResponseDTO> getAllVehicles() {
        return vehicleRepositoy.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleResponseDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepositoy.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        return mapper.toResponseDTO(vehicle);
    }

    @Override
    public Optional<VehicleResponseDTO> getVehicleByVin(String vin) {
        return vehicleRepositoy.findByVin(vin).map(mapper::toResponseDTO);
    }

    @Override
    public List<VehicleResponseDTO> getVehiclesByCustomerId(Long customerId) {
        return vehicleRepositoy.findByCustomerId(customerId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepositoy.deleteById(id);
    }
}
