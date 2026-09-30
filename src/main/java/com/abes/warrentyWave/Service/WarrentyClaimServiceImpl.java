package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.entity.WarrantyClaim;
import com.abes.warrentyWave.repository.WarrentyClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarrentyClaimServiceImpl implements WarrentyClaimService {
    private final WarrentyClaimRepository warrentyClaimRepository;

    @Autowired 
    public WarrentyClaimServiceImpl(WarrentyClaimRepository warrentyClaimRepository){
        this.warrentyClaimRepository = warrentyClaimRepository;
    }

    @Override
    public WarrantyClaim createClaim(WarrantyClaim claim) {
        if (claim == null) {
            throw new IllegalArgumentException("Warranty claim cannot be null");
        }
        claim.setStatus("Submitted");
        return warrentyClaimRepository.save(claim);
    }

    @Override
    public WarrantyClaim getClaimById(Long id) {
        return warrentyClaimRepository.findById(id).orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    @Override
    public List<WarrantyClaim> getAllClaims() {
        return warrentyClaimRepository.findAll();
    }

    @Override
    public WarrantyClaim updateClaimStatus(Long id, String status) {
        WarrantyClaim claim = getClaimById(id);
        claim.setStatus(status);
        return warrentyClaimRepository.save(claim);
    }

    @Override
    public void deleteClaim(Long id) {
        WarrantyClaim claim = getClaimById(id);
        warrentyClaimRepository.delete(claim);
    }

}
