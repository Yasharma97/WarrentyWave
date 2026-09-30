package com.abes.warrentyWave.controller;

import com.abes.warrentyWave.Service.FinanceService;
import com.abes.warrentyWave.entity.FinanceContract;
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
    public FinanceContract createContract(@RequestBody FinanceContract contract) {
        return financeService.createContract(contract);
    }

    @GetMapping
    public List<FinanceContract> getAllContracts() {
        return financeService.getAllContracts();
    }

    @GetMapping("/{id}")
    public FinanceContract getContractById(@PathVariable Long id) {
        return financeService.getContractById(id);
    }

    @GetMapping("/contract-number/{contractNumber}")
    public Optional<FinanceContract> getContractByNumber(@PathVariable String contractNumber) {
        return financeService.getContractByNumber(contractNumber);
    }

    @GetMapping("/customer/{customerId}")
    public List<FinanceContract> getContractsByCustomerId(@PathVariable Long customerId) {
        return financeService.getContractsByCustomerId(customerId);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<FinanceContract> getContractsByVehicleId(@PathVariable Long vehicleId) {
        return financeService.getContractsByVehicleId(vehicleId);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable Long id) {
        financeService.deleteContract(id);
    }
}
