package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.dto.CustomerRequestDTO;
import com.abes.warrentyWave.dto.CustomerResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequest);

    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO getCustomerById(Long id);

    Optional<CustomerResponseDTO> getCustomerByEmail(String email);

    void deleteCustomer(Long id);
}
