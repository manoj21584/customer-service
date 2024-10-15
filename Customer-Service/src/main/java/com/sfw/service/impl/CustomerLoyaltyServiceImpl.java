package com.sfw.service.impl;

import com.sfw.dto.CustomerLoyaltyDTO;
import com.sfw.entities.Customer;
import com.sfw.entities.CustomerLoyalty;
import com.sfw.exceptions.ResourceNotFoundException;
import com.sfw.repositories.CustomerLoyaltyRepository;
import com.sfw.repositories.CustomerRepository;
import com.sfw.service.CustomerLoyaltyService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerLoyaltyServiceImpl implements CustomerLoyaltyService {
    private CustomerLoyaltyRepository customerLoyaltyRepository;
    private CustomerRepository customerRepository;
    @Override
    public CustomerLoyaltyDTO createCustomerLoyalty(CustomerLoyaltyDTO customerLoyaltyDTO) {
        Customer customer = customerRepository.findById(customerLoyaltyDTO.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Customer","customerId", customerLoyaltyDTO.getCustomerId()));
        CustomerLoyalty customerLoyalty = toCustomerLoyalty(customerLoyaltyDTO);
        customerLoyalty.setCustomer(customer);
        customerRepository.save(customer);
        CustomerLoyalty savedCustomerLoyalty = customerLoyaltyRepository.save(customerLoyalty);
        return toCustomerLoyaltyDTO(savedCustomerLoyalty);
    }


    @Override
    public List<CustomerLoyaltyDTO> getAllCustomerLoyalty() {
        List<CustomerLoyalty> allCustomerLoyalty = customerLoyaltyRepository.findAll();
        return allCustomerLoyalty.stream().map(this::toCustomerLoyaltyDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerLoyaltyDTO getCustomerLoyaltyById(Long loyaltyId) {
        CustomerLoyalty customerLoyalty = customerLoyaltyRepository.findById(loyaltyId).orElseThrow(() -> new ResourceNotFoundException("CustomerLoyalty", "loyaltyId", loyaltyId));
        return toCustomerLoyaltyDTO(customerLoyalty);
    }

    @Override
    public String deleteCustomerLoyaltyById(Long loyaltyId) {
        CustomerLoyalty customerLoyalty = customerLoyaltyRepository.findById(loyaltyId).orElseThrow(() -> new ResourceNotFoundException("CustomerLoyalty", "loyaltyId", loyaltyId));
        customerLoyaltyRepository.deleteById(customerLoyalty.getLoyaltyId());
        return "Deleted successfully";
    }

//    @Override
//    public CustomerLoyaltyDTO updateCustomerLoyaltyById(CustomerLoyaltyDTO customerLoyaltyDTO, Integer loyaltyId) {
//        CustomerLoyalty customerLoyalty1 = toCustomerLoyalty(customerLoyaltyDTO);
//        CustomerLoyalty customerLoyalt = customerLoyaltyRepository.findById(loyaltyId).orElseThrow(() -> new ResourceNotFoundException("CustomerLoyalty", "loyaltyId", loyaltyId));
//        Integer customerId = customerLoyalt.getCustomer().getCustomerId();
//        customerLoyalt.setCustomer(customerLoyalty1.getCustomer());
//        customerLoyalt.setTransactionId(customerLoyalty1.getTransactionId());
//        customerLoyalt.setPointsRedeemed(customerLoyalty1.getPointsRedeemed());
//        customerLoyalt.setPointsEarned(customerLoyalty1.getPointsEarned());
//        customerLoyalt.setUpdatedAt(LocalDateTime.now());
//        CustomerLoyalty save = customerLoyaltyRepository.save(customerLoyalt);
//        return toCustomerLoyaltyDTO(save);
//    }

    private CustomerLoyalty toCustomerLoyalty(CustomerLoyaltyDTO customerLoyaltyDTO){
//        CustomerLoyalty customerLoyalty = new CustomerLoyalty();
//        customerLoyalty.setLoyaltyId(customerLoyaltyDTO.getLoyaltyId());
////        customerLoyalty.setCustomerId(customerLoyaltyDTO.getCustomerId());
//        customerLoyalty.setPointsEarned(customerLoyaltyDTO.getPointsEarned());
//        customerLoyalty.setPointsRedeemed(customerLoyaltyDTO.getPointsRedeemed());
//        customerLoyalty.setTransactionId(customerLoyaltyDTO.getTransactionId());
//        customerLoyalty.setCreatedAt(LocalDateTime.now());
//        customerLoyalty.setUpdatedAt(LocalDateTime.now());
//        return customerLoyalty;
        CustomerLoyalty customerLoyalty = new CustomerLoyalty();
        customerLoyalty.setLoyaltyId(customerLoyaltyDTO.getLoyaltyId());
        customerLoyalty.setPointsEarned(customerLoyaltyDTO.getPointsEarned());
        customerLoyalty.setPointsRedeemed(customerLoyaltyDTO.getPointsRedeemed());
        customerLoyalty.setTransactionId(customerLoyaltyDTO.getTransactionId());
        // Set audit fields using current time for creation and modification timestamps

        customerLoyalty.setCreatedDate(LocalDateTime.now());
        customerLoyalty.setModifiedDate(LocalDateTime.now());
        Optional<Customer> byId = customerRepository.findById(customerLoyaltyDTO.getCustomerId());

        customerLoyalty.setCreatedBy(byId.get().getCustomerName()) ;// Replace with actual user information
        customerLoyalty.setModifiedBy(byId.get().getCustomerName()); // Replace with actual user information
        return customerLoyalty;
    }

    private  CustomerLoyaltyDTO toCustomerLoyaltyDTO(CustomerLoyalty customerLoyalty) {
//        CustomerLoyaltyDTO dto = new CustomerLoyaltyDTO();
//        dto.setLoyaltyId(customerLoyalty.getLoyaltyId());
//        dto.setCustomerId(customerLoyalty.getCustomer().getCustomerId());
//        dto.setPointsEarned(customerLoyalty.getPointsEarned());
//        dto.setPointsRedeemed(customerLoyalty.getPointsRedeemed());
//        dto.setTransactionId(customerLoyalty.getTransactionId());
//        dto.setCustomer(customerLoyalty.getCustomer());
//        dto.setCreatedAt(LocalDateTime.now());
//        dto.setUpdatedAt(LocalDateTime.now());
//
//        return dto;
        CustomerLoyaltyDTO dto = new CustomerLoyaltyDTO();
        dto.setLoyaltyId(customerLoyalty.getLoyaltyId());
        dto.setCustomerId(customerLoyalty.getCustomer().getCustomerId());
        dto.setPointsEarned(customerLoyalty.getPointsEarned());
        dto.setPointsRedeemed(customerLoyalty.getPointsRedeemed());
        dto.setTransactionId(customerLoyalty.getTransactionId());
        // Set audit fields from the entity
        dto.setCreatedDate(LocalDateTime.now());
        dto.setModifiedDate(LocalDateTime.now());
        dto.setCreatedBy(customerLoyalty.getCustomer().getCustomerName());
        dto.setModifiedBy(customerLoyalty.getCustomer().getCustomerName());
        return dto;
    }
}
