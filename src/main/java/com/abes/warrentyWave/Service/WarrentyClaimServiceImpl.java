package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.dto.ClaimStatusUpdateDTO;
import com.abes.warrentyWave.dto.WarrantyClaimRequestDTO;
import com.abes.warrentyWave.dto.WarrantyClaimResponseDTO;
import com.abes.warrentyWave.entity.WarrantyClaim;
import com.abes.warrentyWave.mapper.EntityDtoMapper;
import com.abes.warrentyWave.repository.WarrentyClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarrentyClaimServiceImpl implements WarrentyClaimService {

    private final WarrentyClaimRepository warrentyClaimRepository;
    private final EntityDtoMapper mapper;

    @Autowired
    public WarrentyClaimServiceImpl(WarrentyClaimRepository warrentyClaimRepository, EntityDtoMapper mapper) {
        this.warrentyClaimRepository = warrentyClaimRepository;
        this.mapper = mapper;
    }

    @Override
    public WarrantyClaimResponseDTO createClaim(WarrantyClaimRequestDTO claimRequest) {
        if (claimRequest == null) {
            throw new IllegalArgumentException("Warranty claim request cannot be null");
        }
        WarrantyClaim claim = mapper.toEntity(claimRequest);
        WarrantyClaim saved = warrentyClaimRepository.save(claim);
        return mapper.toResponseDTO(saved);
    }

    @Override
    public WarrantyClaimResponseDTO getClaimById(Long id) {
        WarrantyClaim claim = warrentyClaimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + id));
        return mapper.toResponseDTO(claim);
    }

    @Override
    public List<WarrantyClaimResponseDTO> getAllClaims() {
        return warrentyClaimRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WarrantyClaimResponseDTO updateClaimStatus(Long id, String status) {
        WarrantyClaim claim = warrentyClaimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + id));
        if (status != null && !status.isBlank()) {
            claim.setStatus(status);
        }
        WarrantyClaim updated = warrentyClaimRepository.save(claim);
        return mapper.toResponseDTO(updated);
    }

    @Override
    public WarrantyClaimResponseDTO updateClaimStatus(Long id, ClaimStatusUpdateDTO statusDto) {
        WarrantyClaim claim = warrentyClaimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + id));
        if (statusDto != null) {
            if (statusDto.getStatus() != null && !statusDto.getStatus().isBlank()) {
                claim.setStatus(statusDto.getStatus());
            }
            if (statusDto.getRemarks() != null && !statusDto.getRemarks().isBlank()) {
                claim.setRemarks(statusDto.getRemarks());
            }
        }
        WarrantyClaim updated = warrentyClaimRepository.save(claim);
        return mapper.toResponseDTO(updated);
    }

    @Override
    public void deleteClaim(Long id) {
        WarrantyClaim claim = warrentyClaimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + id));
        warrentyClaimRepository.delete(claim);
    }
}
