package com.sfw.controllers;

import com.sfw.dto.CustomerLoyaltyDTO;
import com.sfw.service.CustomerLoyaltyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customerLoyalty")
public class CustomerLoyaltyController {
    private CustomerLoyaltyService customerLoyaltyService;

    @PostMapping
    public ResponseEntity<CustomerLoyaltyDTO> createCustomerLoyalty(@RequestBody CustomerLoyaltyDTO customerLoyaltyDTO) {
        CustomerLoyaltyDTO response = customerLoyaltyService.createCustomerLoyalty(customerLoyaltyDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CustomerLoyaltyDTO>> getAllCustomerLoyalty() {
        List<CustomerLoyaltyDTO> response = customerLoyaltyService.getAllCustomerLoyalty();
        return ResponseEntity.ok(response);

    }
    @GetMapping("/{loyaltyId}")
    public ResponseEntity<CustomerLoyaltyDTO> getCustomerLoyaltyById(@PathVariable Long loyaltyId) {
        CustomerLoyaltyDTO response = customerLoyaltyService.getCustomerLoyaltyById(loyaltyId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{loyaltyId}")
    public ResponseEntity<String> deleteCustomerLoyaltyById(@PathVariable Long loyaltyId) {
        String response = customerLoyaltyService.deleteCustomerLoyaltyById(loyaltyId);
        return ResponseEntity.ok(response);
    }
//    @PutMapping("/{loyaltyId}")
//    public ResponseEntity<CustomerLoyaltyDTO> updateCustomerLoyaltyById(@RequestBody CustomerLoyaltyDTO customerLoyaltyDTO,@PathVariable Integer loyaltyId) {
//        CustomerLoyaltyDTO response = customerLoyaltyService.updateCustomerLoyaltyById(customerLoyaltyDTO,loyaltyId);
//        return ResponseEntity.ok(response);
//    }
}
