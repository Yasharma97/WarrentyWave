package com.abes.warrentyWave.mapper;

import com.abes.warrentyWave.dto.*;
import com.abes.warrentyWave.entity.Customer;
import com.abes.warrentyWave.entity.FinanceContract;
import com.abes.warrentyWave.entity.Vehicle;
import com.abes.warrentyWave.entity.WarrantyClaim;
import com.abes.warrentyWave.repository.CustomerRepository;
import com.abes.warrentyWave.repository.FinanceContractRepository;
import com.abes.warrentyWave.repository.VehicleRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoMapper {

    private final CustomerRepository customerRepository;
    private final VehicleRepositoy vehicleRepository;
    private final FinanceContractRepository financeContractRepository;

    @Autowired
    public EntityDtoMapper(CustomerRepository customerRepository,
                           VehicleRepositoy vehicleRepository,
                           FinanceContractRepository financeContractRepository) {
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.financeContractRepository = financeContractRepository;
    }

    // ==========================================
    // WARRANTY CLAIM MAPPINGS
    // ==========================================

    public WarrantyClaim toEntity(WarrantyClaimRequestDTO dto) {
        if (dto == null) return null;
        WarrantyClaim claim = new WarrantyClaim();
        claim.setCustomerId(dto.getCustomerId());
        claim.setVehicleId(dto.getVehicleId());
        claim.setContractId(dto.getContractId());
        claim.setClaimDate(dto.getClaimDate());
        claim.setStatus(dto.getStatus() != null && !dto.getStatus().isBlank() ? dto.getStatus() : "Submitted");
        claim.setDescription(dto.getDescription());
        claim.setClaimAmount(dto.getClaimAmount());
        claim.setRemarks(dto.getRemarks());
        return claim;
    }

    public WarrantyClaimResponseDTO toResponseDTO(WarrantyClaim claim) {
        if (claim == null) return null;

        String customerName = "N/A";
        if (claim.getCustomerId() != null) {
            customerName = customerRepository.findById(claim.getCustomerId())
                    .map(c -> (c.getFirstName() != null ? c.getFirstName() : "") + " " + (c.getLastName() != null ? c.getLastName() : ""))
                    .map(String::trim)
                    .orElse("Cust #" + claim.getCustomerId());
        }

        String vehicleInfo = "N/A";
        if (claim.getVehicleId() != null) {
            vehicleInfo = vehicleRepository.findById(claim.getVehicleId())
                    .map(v -> (v.getYear() != null ? v.getYear() + " " : "") +
                              (v.getMake() != null ? v.getMake() + " " : "") +
                              (v.getModel() != null ? v.getModel() : "") +
                              (v.getVin() != null ? " (" + v.getVin() + ")" : ""))
                    .map(String::trim)
                    .orElse("Veh #" + claim.getVehicleId());
        }

        WarrantyClaimResponseDTO responseDTO = new WarrantyClaimResponseDTO();
        responseDTO.setId(claim.getId());
        responseDTO.setCustomerId(claim.getCustomerId());
        responseDTO.setCustomerName(customerName);
        responseDTO.setVehicleId(claim.getVehicleId());
        responseDTO.setVehicleInfo(vehicleInfo);
        responseDTO.setContractId(claim.getContractId());
        responseDTO.setClaimDate(claim.getClaimDate());
        responseDTO.setStatus(claim.getStatus());
        responseDTO.setDescription(claim.getDescription());
        responseDTO.setClaimAmount(claim.getClaimAmount());
        responseDTO.setRemarks(claim.getRemarks());
        return responseDTO;
    }

    // ==========================================
    // VEHICLE MAPPINGS
    // ==========================================

    public Vehicle toEntity(VehicleRequestDTO dto) {
        if (dto == null) return null;
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(dto.getVin());
        vehicle.setMake(dto.getMake());
        vehicle.setModel(dto.getModel());
        vehicle.setYear(dto.getYear());
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setMileage(dto.getMileage());
        vehicle.setCustomerId(dto.getCustomerId());
        return vehicle;
    }

    public VehicleResponseDTO toResponseDTO(Vehicle vehicle) {
        if (vehicle == null) return null;

        String ownerName = "Unassigned";
        if (vehicle.getCustomerId() != null) {
            ownerName = customerRepository.findById(vehicle.getCustomerId())
                    .map(c -> (c.getFirstName() != null ? c.getFirstName() : "") + " " + (c.getLastName() != null ? c.getLastName() : ""))
                    .map(String::trim)
                    .orElse("Cust #" + vehicle.getCustomerId());
        }

        VehicleResponseDTO responseDTO = new VehicleResponseDTO();
        responseDTO.setId(vehicle.getId());
        responseDTO.setVin(vehicle.getVin());
        responseDTO.setMake(vehicle.getMake());
        responseDTO.setModel(vehicle.getModel());
        responseDTO.setYear(vehicle.getYear());
        responseDTO.setLicensePlate(vehicle.getLicensePlate());
        responseDTO.setMileage(vehicle.getMileage());
        responseDTO.setCustomerId(vehicle.getCustomerId());
        responseDTO.setOwnerName(ownerName);
        return responseDTO;
    }

    // ==========================================
    // FINANCE CONTRACT MAPPINGS
    // ==========================================

    public FinanceContract toEntity(FinanceContractRequestDTO dto) {
        if (dto == null) return null;
        FinanceContract contract = new FinanceContract();
        contract.setContractNumber(dto.getContractNumber());
        contract.setCustomerId(dto.getCustomerId());
        contract.setVehicleId(dto.getVehicleId());
        contract.setLoanAmount(dto.getLoanAmount());
        contract.setDownPayment(dto.getDownPayment());
        contract.setInterestRate(dto.getInterestRate());
        contract.setTermMonths(dto.getTermMonths());
        contract.setMonthlyPayment(dto.getMonthlyPayment());
        contract.setStartDate(dto.getStartDate());
        contract.setEndDate(dto.getEndDate());
        contract.setStatus(dto.getStatus() != null && !dto.getStatus().isBlank() ? dto.getStatus() : "ACTIVE");
        return contract;
    }

    public FinanceContractResponseDTO toResponseDTO(FinanceContract contract) {
        if (contract == null) return null;

        String customerName = "N/A";
        if (contract.getCustomerId() != null) {
            customerName = customerRepository.findById(contract.getCustomerId())
                    .map(c -> (c.getFirstName() != null ? c.getFirstName() : "") + " " + (c.getLastName() != null ? c.getLastName() : ""))
                    .map(String::trim)
                    .orElse("Cust #" + contract.getCustomerId());
        }

        String vehicleInfo = "N/A";
        if (contract.getVehicleId() != null) {
            vehicleInfo = vehicleRepository.findById(contract.getVehicleId())
                    .map(v -> (v.getYear() != null ? v.getYear() + " " : "") +
                              (v.getMake() != null ? v.getMake() + " " : "") +
                              (v.getModel() != null ? v.getModel() : ""))
                    .map(String::trim)
                    .orElse("Veh #" + contract.getVehicleId());
        }

        Double financedAmount = contract.getLoanAmount();
        if (contract.getLoanAmount() != null && contract.getDownPayment() != null) {
            financedAmount = contract.getLoanAmount() - contract.getDownPayment();
        }

        FinanceContractResponseDTO responseDTO = new FinanceContractResponseDTO();
        responseDTO.setId(contract.getId());
        responseDTO.setContractNumber(contract.getContractNumber());
        responseDTO.setCustomerId(contract.getCustomerId());
        responseDTO.setCustomerName(customerName);
        responseDTO.setVehicleId(contract.getVehicleId());
        responseDTO.setVehicleInfo(vehicleInfo);
        responseDTO.setLoanAmount(contract.getLoanAmount());
        responseDTO.setDownPayment(contract.getDownPayment());
        responseDTO.setFinancedAmount(financedAmount);
        responseDTO.setInterestRate(contract.getInterestRate());
        responseDTO.setTermMonths(contract.getTermMonths());
        responseDTO.setMonthlyPayment(contract.getMonthlyPayment());
        responseDTO.setStartDate(contract.getStartDate());
        responseDTO.setEndDate(contract.getEndDate());
        responseDTO.setStatus(contract.getStatus());
        return responseDTO;
    }

    // ==========================================
    // CUSTOMER MAPPINGS
    // ==========================================

    public Customer toEntity(CustomerRequestDTO dto) {
        if (dto == null) return null;
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setAddress(dto.getAddress());
        customer.setCity(dto.getCity());
        customer.setState(dto.getState());
        customer.setZipCode(dto.getZipCode());
        return customer;
    }

    public CustomerResponseDTO toResponseDTO(Customer customer) {
        if (customer == null) return null;
        String fullName = ((customer.getFirstName() != null ? customer.getFirstName() : "") + " " +
                           (customer.getLastName() != null ? customer.getLastName() : "")).trim();

        CustomerResponseDTO responseDTO = new CustomerResponseDTO();
        responseDTO.setId(customer.getId());
        responseDTO.setFirstName(customer.getFirstName());
        responseDTO.setLastName(customer.getLastName());
        responseDTO.setFullName(fullName);
        responseDTO.setEmail(customer.getEmail());
        responseDTO.setPhoneNumber(customer.getPhoneNumber());
        responseDTO.setAddress(customer.getAddress());
        responseDTO.setCity(customer.getCity());
        responseDTO.setState(customer.getState());
        responseDTO.setZipCode(customer.getZipCode());
        return responseDTO;
    }
}
