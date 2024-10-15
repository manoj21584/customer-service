package com.sfw.service;

import com.sfw.dto.CustomerDTO;


import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long customerId);
    List<CustomerDTO> getAllCustomer();
    CustomerDTO updateCustomer(CustomerDTO customerDTO, Long customerId);
    String deleteCustomerById(Long customerId);
}
