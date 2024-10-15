package com.sfw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoyaltyDTO {

    private Long loyaltyId;
    private Long customerId; // To reference the customer entity
    private Integer pointsEarned;
    private Integer pointsRedeemed;
    private String transactionId;
    private LocalDateTime createdDate; // Changed to match BaseEntity fields
    private LocalDateTime modifiedDate; // Changed to match BaseEntity fields
    private String createdBy; // User who created this record
    private String modifiedBy; // User who last modified this record
}
