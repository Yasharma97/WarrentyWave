package com.abes.warrentyWave.Service;

import com.abes.warrentyWave.dto.CustomerRequestDTO;
import com.abes.warrentyWave.dto.CustomerResponseDTO;
import com.abes.warrentyWave.entity.Customer;
import com.abes.warrentyWave.mapper.EntityDtoMapper;
import com.abes.warrentyWave.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityDtoMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, EntityDtoMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequest) {
        if (customerRequest == null) {
            throw new IllegalArgumentException("Customer request cannot be null");
        }
        Customer customer = mapper.toEntity(customerRequest);
        Customer saved = customerRepository.save(customer);
        return mapper.toResponseDTO(saved);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return mapper.toResponseDTO(customer);
    }

    @Override
    public Optional<CustomerResponseDTO> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).map(mapper::toResponseDTO);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
