package com.sfw.controllers;

import com.sfw.dto.CustomerDTO;
import com.sfw.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        CustomerDTO customerDto = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(customerDto);

    }
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomer(){
        List<CustomerDTO> allCustomer = customerService.getAllCustomer();
        return ResponseEntity.ok(allCustomer);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long customerId){
        CustomerDTO customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO,@PathVariable Long customerId){
        CustomerDTO customerDto = customerService.updateCustomer(customerDTO,customerId);
        return ResponseEntity.ok(customerDto);

    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long customerId){
        String response = customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok(response);

    }


}
