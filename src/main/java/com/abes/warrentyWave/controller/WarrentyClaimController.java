package com.abes.warrentyWave.controller;

import com.abes.warrentyWave.Service.WarrentyClaimService;
import com.abes.warrentyWave.entity.WarrantyClaim;
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
    public WarrantyClaim createClaim(@RequestBody WarrantyClaim claim) {
        return warrentyClaimService.createClaim(claim);
    }

    @GetMapping
    public List<WarrantyClaim> getAllClaims() {
        return warrentyClaimService.getAllClaims();
    }

    @GetMapping("/{id}")
    public WarrantyClaim getClaimById(@PathVariable Long id) {
        return warrentyClaimService.getClaimById(id);
    }

    @PutMapping("/{id}/status")
    public WarrantyClaim updateClaimStatus(@PathVariable Long id, @RequestParam String status) {
        return warrentyClaimService.updateClaimStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteClaim(@PathVariable Long id) {
        warrentyClaimService.deleteClaim(id);
    }
}
