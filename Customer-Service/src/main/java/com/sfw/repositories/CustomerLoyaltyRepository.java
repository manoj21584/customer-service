package com.sfw.repositories;

import com.sfw.entities.CustomerLoyalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerLoyaltyRepository extends JpaRepository<CustomerLoyalty,Long> {
}
