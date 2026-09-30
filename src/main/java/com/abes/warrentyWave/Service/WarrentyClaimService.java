package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.entity.WarrantyClaim;
import java.util.List;

public interface WarrentyClaimService {

    WarrantyClaim createClaim(WarrantyClaim claim);

    WarrantyClaim getClaimById(Long id);

    List<WarrantyClaim> getAllClaims();

    WarrantyClaim updateClaimStatus(Long id, String status);

    void deleteClaim(Long id);
}
