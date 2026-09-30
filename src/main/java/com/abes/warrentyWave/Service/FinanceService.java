package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.dto.FinanceContractRequestDTO;
import com.abes.warrentyWave.dto.FinanceContractResponseDTO;
import com.abes.warrentyWave.entity.FinanceContract;
import com.abes.warrentyWave.mapper.EntityDtoMapper;
import com.abes.warrentyWave.repository.FinanceContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FinanceService {

    private final FinanceContractRepository financeContractRepository;
    private final EntityDtoMapper mapper;

    @Autowired
    public FinanceService(FinanceContractRepository financeContractRepository, EntityDtoMapper mapper) {
        this.financeContractRepository = financeContractRepository;
        this.mapper = mapper;
    }

    public FinanceContractResponseDTO createContract(FinanceContractRequestDTO contractRequest) {
        if (contractRequest == null) {
            throw new IllegalArgumentException("Finance contract cannot be null");
        }
        FinanceContract contract = mapper.toEntity(contractRequest);
        if (contract.getStatus() == null || contract.getStatus().trim().isEmpty()) {
            contract.setStatus("ACTIVE");
        }
        FinanceContract saved = financeContractRepository.save(contract);
        return mapper.toResponseDTO(saved);
    }

    public List<FinanceContractResponseDTO> getAllContracts() {
        return financeContractRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public FinanceContractResponseDTO getContractById(Long id) {
        FinanceContract contract = financeContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance contract not found with id: " + id));
        return mapper.toResponseDTO(contract);
    }

    public Optional<FinanceContractResponseDTO> getContractByNumber(String contractNumber) {
        return financeContractRepository.findByContractNumber(contractNumber).map(mapper::toResponseDTO);
    }

    public List<FinanceContractResponseDTO> getContractsByCustomerId(Long customerId) {
        return financeContractRepository.findByCustomerId(customerId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<FinanceContractResponseDTO> getContractsByVehicleId(Long vehicleId) {
        return financeContractRepository.findByVehicleId(vehicleId).stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteContract(Long id) {
        financeContractRepository.deleteById(id);
    }
}
