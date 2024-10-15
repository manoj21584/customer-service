package com.sfw.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_loyalty")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerLoyalty extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loyalty_id")
    private Long loyaltyId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("customerLoyalties")
    private Customer customer;

    @Column(name = "points_earned", nullable = false)
    private Integer pointsEarned;

    @Column(name = "points_redeemed", nullable = false)
    private Integer pointsRedeemed;

    @Column(name = "transaction_id", length = 255)
    private String transactionId;
}
