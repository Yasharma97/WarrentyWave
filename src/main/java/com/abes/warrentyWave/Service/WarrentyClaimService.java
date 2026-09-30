package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.dto.ClaimStatusUpdateDTO;
import com.abes.warrentyWave.dto.WarrantyClaimRequestDTO;
import com.abes.warrentyWave.dto.WarrantyClaimResponseDTO;
import java.util.List;

public interface WarrentyClaimService {

    WarrantyClaimResponseDTO createClaim(WarrantyClaimRequestDTO claimRequest);

    WarrantyClaimResponseDTO getClaimById(Long id);

    List<WarrantyClaimResponseDTO> getAllClaims();

    WarrantyClaimResponseDTO updateClaimStatus(Long id, String status);

    WarrantyClaimResponseDTO updateClaimStatus(Long id, ClaimStatusUpdateDTO statusDto);

    void deleteClaim(Long id);
}
