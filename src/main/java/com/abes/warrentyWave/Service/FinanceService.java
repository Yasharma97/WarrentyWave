package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.entity.FinanceContract;
import com.abes.warrentyWave.repository.FinanceContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceService {

    private final FinanceContractRepository financeContractRepository;

    @Autowired
    public FinanceService(FinanceContractRepository financeContractRepository) {
        this.financeContractRepository = financeContractRepository;
    }

    public FinanceContract createContract(FinanceContract contract) {
        if (contract == null) {
            throw new IllegalArgumentException("Finance contract cannot be null");
        }
        if (contract.getStatus() == null || contract.getStatus().trim().isEmpty()) {
            contract.setStatus("ACTIVE");
        }
        return financeContractRepository.save(contract);
    }

    public List<FinanceContract> getAllContracts() {
        return financeContractRepository.findAll();
    }

    public FinanceContract getContractById(Long id) {
        return financeContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance contract not found with id: " + id));
    }

    public Optional<FinanceContract> getContractByNumber(String contractNumber) {
        return financeContractRepository.findByContractNumber(contractNumber);
    }

    public List<FinanceContract> getContractsByCustomerId(Long customerId) {
        return financeContractRepository.findByCustomerId(customerId);
    }

    public List<FinanceContract> getContractsByVehicleId(Long vehicleId) {
        return financeContractRepository.findByVehicleId(vehicleId);
    }

    public void deleteContract(Long id) {
        financeContractRepository.deleteById(id);
    }
}
