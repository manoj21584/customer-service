package com.sfw.service;

import com.sfw.dto.CustomerLoyaltyDTO;

import java.util.List;

public interface CustomerLoyaltyService {
    CustomerLoyaltyDTO createCustomerLoyalty(CustomerLoyaltyDTO customerLoyaltyDTO);
    List<CustomerLoyaltyDTO> getAllCustomerLoyalty();
    CustomerLoyaltyDTO getCustomerLoyaltyById(Long loyaltyId);
//    CustomerLoyaltyDTO updateCustomerLoyaltyById(CustomerLoyaltyDTO customerLoyaltyDTO,Integer loyaltyId);
    String deleteCustomerLoyaltyById(Long loyaltyId);

}
