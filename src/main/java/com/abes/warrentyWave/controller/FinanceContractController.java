package com.abes.warrentyWave.controller;

import com.abes.warrentyWave.Service.FinanceService;
import com.abes.warrentyWave.dto.FinanceContractRequestDTO;
import com.abes.warrentyWave.dto.FinanceContractResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/finance-contracts")
public class FinanceContractController {

    private final FinanceService financeService;

    @Autowired
    public FinanceContractController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @PostMapping
    public FinanceContractResponseDTO createContract(@RequestBody FinanceContractRequestDTO contract) {
        return financeService.createContract(contract);
    }

    @GetMapping
    public List<FinanceContractResponseDTO> getAllContracts() {
        return financeService.getAllContracts();
    }

    @GetMapping("/{id}")
    public FinanceContractResponseDTO getContractById(@PathVariable Long id) {
        return financeService.getContractById(id);
    }

    @GetMapping("/contract-number/{contractNumber}")
    public Optional<FinanceContractResponseDTO> getContractByNumber(@PathVariable String contractNumber) {
        return financeService.getContractByNumber(contractNumber);
    }

    @GetMapping("/customer/{customerId}")
    public List<FinanceContractResponseDTO> getContractsByCustomerId(@PathVariable Long customerId) {
        return financeService.getContractsByCustomerId(customerId);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<FinanceContractResponseDTO> getContractsByVehicleId(@PathVariable Long vehicleId) {
        return financeService.getContractsByVehicleId(vehicleId);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable Long id) {
        financeService.deleteContract(id);
    }
}
