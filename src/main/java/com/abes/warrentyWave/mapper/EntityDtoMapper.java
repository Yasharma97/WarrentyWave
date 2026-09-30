package com.abes.warrentyWave.mapper;

import com.abes.warrentyWave.dto.ClaimStatusUpdateDTO;
import com.abes.warrentyWave.dto.CustomerRequestDTO;
import com.abes.warrentyWave.dto.CustomerResponseDTO;
import com.abes.warrentyWave.dto.FinanceContractRequestDTO;
import com.abes.warrentyWave.dto.FinanceContractResponseDTO;
import com.abes.warrentyWave.dto.VehicleRequestDTO;
import com.abes.warrentyWave.dto.VehicleResponseDTO;
import com.abes.warrentyWave.dto.WarrantyClaimRequestDTO;
import com.abes.warrentyWave.dto.WarrantyClaimResponseDTO;
import com.abes.warrentyWave.entity.Customer;
import com.abes.warrentyWave.entity.FinanceContract;
import com.abes.warrentyWave.entity.Vehicle;
import com.abes.warrentyWave.entity.WarrantyClaim;
import com.abes.warrentyWave.repository.CustomerRepository;
import com.abes.warrentyWave.repository.FinanceContractRepository;
import com.abes.warrentyWave.repository.VehicleRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public WarrantyClaim toEntity(WarrantyClaimRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        WarrantyClaim claim = new WarrantyClaim();
        claim.setCustomerId(dto.getCustomerId());
        claim.setVehicleId(dto.getVehicleId());
        claim.setContractId(dto.getContractId());
        claim.setClaimDate(dto.getClaimDate());

        String status = dto.getStatus();
        if (status == null || status.trim().isEmpty()) {
            claim.setStatus("Submitted");
        } else {
            claim.setStatus(status.trim());
        }

        claim.setDescription(dto.getDescription());
        claim.setClaimAmount(dto.getClaimAmount());
        claim.setRemarks(dto.getRemarks());
        return claim;
    }

    public WarrantyClaimResponseDTO toResponseDTO(WarrantyClaim claim) {
        if (claim == null) {
            return null;
        }

        String customerName = "N/A";
        if (claim.getCustomerId() != null) {
            Optional<Customer> custOpt = customerRepository.findById(claim.getCustomerId());
            if (custOpt.isPresent()) {
                Customer c = custOpt.get();
                String first = c.getFirstName() != null ? c.getFirstName() : "";
                String last = c.getLastName() != null ? c.getLastName() : "";
                customerName = (first + " " + last).trim();
            } else {
                customerName = "Cust #" + claim.getCustomerId();
            }
        }

        String vehicleInfo = "N/A";
        if (claim.getVehicleId() != null) {
            Optional<Vehicle> vehOpt = vehicleRepository.findById(claim.getVehicleId());
            if (vehOpt.isPresent()) {
                Vehicle v = vehOpt.get();
                StringBuilder sb = new StringBuilder();
                if (v.getYear() != null) {
                    sb.append(v.getYear()).append(" ");
                }
                if (v.getMake() != null) {
                    sb.append(v.getMake()).append(" ");
                }
                if (v.getModel() != null) {
                    sb.append(v.getModel());
                }
                if (v.getVin() != null) {
                    sb.append(" (").append(v.getVin()).append(")");
                }
                vehicleInfo = sb.toString().trim();
            } else {
                vehicleInfo = "Veh #" + claim.getVehicleId();
            }
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

    public Vehicle toEntity(VehicleRequestDTO dto) {
        if (dto == null) {
            return null;
        }
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
        if (vehicle == null) {
            return null;
        }

        String ownerName = "Unassigned";
        if (vehicle.getCustomerId() != null) {
            Optional<Customer> custOpt = customerRepository.findById(vehicle.getCustomerId());
            if (custOpt.isPresent()) {
                Customer c = custOpt.get();
                String first = c.getFirstName() != null ? c.getFirstName() : "";
                String last = c.getLastName() != null ? c.getLastName() : "";
                ownerName = (first + " " + last).trim();
            } else {
                ownerName = "Cust #" + vehicle.getCustomerId();
            }
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

    public FinanceContract toEntity(FinanceContractRequestDTO dto) {
        if (dto == null) {
            return null;
        }
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

        String status = dto.getStatus();
        if (status == null || status.trim().isEmpty()) {
            contract.setStatus("ACTIVE");
        } else {
            contract.setStatus(status.trim());
        }

        return contract;
    }

    public FinanceContractResponseDTO toResponseDTO(FinanceContract contract) {
        if (contract == null) {
            return null;
        }

        String customerName = "N/A";
        if (contract.getCustomerId() != null) {
            Optional<Customer> custOpt = customerRepository.findById(contract.getCustomerId());
            if (custOpt.isPresent()) {
                Customer c = custOpt.get();
                String first = c.getFirstName() != null ? c.getFirstName() : "";
                String last = c.getLastName() != null ? c.getLastName() : "";
                customerName = (first + " " + last).trim();
            } else {
                customerName = "Cust #" + contract.getCustomerId();
            }
        }

        String vehicleInfo = "N/A";
        if (contract.getVehicleId() != null) {
            Optional<Vehicle> vehOpt = vehicleRepository.findById(contract.getVehicleId());
            if (vehOpt.isPresent()) {
                Vehicle v = vehOpt.get();
                StringBuilder sb = new StringBuilder();
                if (v.getYear() != null) {
                    sb.append(v.getYear()).append(" ");
                }
                if (v.getMake() != null) {
                    sb.append(v.getMake()).append(" ");
                }
                if (v.getModel() != null) {
                    sb.append(v.getModel());
                }
                vehicleInfo = sb.toString().trim();
            } else {
                vehicleInfo = "Veh #" + contract.getVehicleId();
            }
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

    public Customer toEntity(CustomerRequestDTO dto) {
        if (dto == null) {
            return null;
        }
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
        if (customer == null) {
            return null;
        }
        String first = customer.getFirstName() != null ? customer.getFirstName() : "";
        String last = customer.getLastName() != null ? customer.getLastName() : "";
        String fullName = (first + " " + last).trim();

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
