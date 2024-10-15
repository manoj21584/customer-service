package com.sfw.service.impl;

import com.sfw.dto.CustomerDTO;
import com.sfw.dto.CustomerLoyaltyDTO;
import com.sfw.entities.Customer;
import com.sfw.entities.CustomerLoyalty;
import com.sfw.exceptions.ResourceNotFoundException;
import com.sfw.repositories.CustomerLoyaltyRepository;
import com.sfw.repositories.CustomerRepository;
import com.sfw.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerLoyaltyRepository customerLoyaltyRepository;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = toCustomerEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return toCustomerDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));
        return toCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> allCustomerList = customerRepository.findAll();
        return allCustomerList.stream().map(this::toCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Long customerId) {
        Customer customerFromDb = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));

        customerFromDb.setCustomerName(customerDTO.getCustomerName());
        customerFromDb.setCustomerEmail(customerDTO.getCustomerEmail());
        customerFromDb.setCustomerAddress(customerDTO.getCustomerAddress());
        customerFromDb.setCustomerPhone(customerDTO.getCustomerPhone());
        customerFromDb.setCustomerLoyaltyPoints(customerDTO.getCustomerLoyaltyPoints());
        customerFromDb.setModifiedBy(customerDTO.getCustomerName());
        customerFromDb.setModifiedDate(LocalDateTime.now());


        Customer updatedCustomer = customerRepository.save(customerFromDb);
        return toCustomerDTO(updatedCustomer);
    }

    @Override
    public String deleteCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));
        customerRepository.deleteById(customer.getCustomerId());
        return "Deleted Successfully";
    }

    private Customer toCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setCustomerPhone(customerDTO.getCustomerPhone());
        customer.setCustomerAddress(customerDTO.getCustomerAddress());
        customer.setCustomerLoyaltyPoints(customerDTO.getCustomerLoyaltyPoints());

        // Set BaseEntity fields
        customer.setCreatedDate(customerDTO.getCreatedDate() != null ? customerDTO.getCreatedDate() : LocalDateTime.now());
        customer.setCreatedBy(customerDTO.getCustomerName());
        customer.setModifiedDate(LocalDateTime.now());
        customer.setModifiedBy(customerDTO.getCustomerName());

        return customer;
    }

    private CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerEmail(customer.getCustomerEmail());
        customerDTO.setCustomerAddress(customer.getCustomerAddress());
        customerDTO.setCustomerPhone(customer.getCustomerPhone());
        customerDTO.setCustomerLoyaltyPoints(customer.getCustomerLoyaltyPoints());

        // Set BaseEntity fields
        customerDTO.setCreatedDate(customer.getCreatedDate());
        customerDTO.setCreatedBy(customer.getCustomerName());
        customerDTO.setModifiedDate(LocalDateTime.now());
        customerDTO.setModifiedBy(customer.getCustomerName());

        return customerDTO;
    }
}
