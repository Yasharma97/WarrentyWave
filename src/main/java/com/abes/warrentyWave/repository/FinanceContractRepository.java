package com.abes.warrentyWave.repository;

import com.abes.warrentyWave.entity.FinanceContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinanceContractRepository extends JpaRepository<FinanceContract, Long> {
    Optional<FinanceContract> findByContractNumber(String contractNumber);
    List<FinanceContract> findByCustomerId(Long customerId);
    List<FinanceContract> findByVehicleId(Long vehicleId);
}
