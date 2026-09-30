package com.abes.warrentyWave.controller;

import com.abes.warrentyWave.Service.WarrentyClaimService;
import com.abes.warrentyWave.dto.ClaimStatusUpdateDTO;
import com.abes.warrentyWave.dto.WarrantyClaimRequestDTO;
import com.abes.warrentyWave.dto.WarrantyClaimResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class WarrentyClaimController {

    private final WarrentyClaimService warrentyClaimService;

    @Autowired
    public WarrentyClaimController(WarrentyClaimService warrentyClaimService) {
        this.warrentyClaimService = warrentyClaimService;
    }

    @PostMapping
    public WarrantyClaimResponseDTO createClaim(@RequestBody WarrantyClaimRequestDTO claim) {
        return warrentyClaimService.createClaim(claim);
    }

    @GetMapping
    public List<WarrantyClaimResponseDTO> getAllClaims() {
        return warrentyClaimService.getAllClaims();
    }

    @GetMapping("/{id}")
    public WarrantyClaimResponseDTO getClaimById(@PathVariable Long id) {
        return warrentyClaimService.getClaimById(id);
    }

    @PutMapping("/{id}/status")
    public WarrantyClaimResponseDTO updateClaimStatus(@PathVariable Long id,
                                                      @RequestParam(required = false) String status,
                                                      @RequestBody(required = false) ClaimStatusUpdateDTO statusDto) {
        if (statusDto != null) {
            return warrentyClaimService.updateClaimStatus(id, statusDto);
        }
        return warrentyClaimService.updateClaimStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteClaim(@PathVariable Long id) {
        warrentyClaimService.deleteClaim(id);
    }
}
